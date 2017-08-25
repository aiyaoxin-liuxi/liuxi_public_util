/*
 * MapList.java
 */
package org.pub.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.pub.util.uuid.Uuid;

/**
 * Map + List
 *
 */
public class MapList<K, V> implements SortedMap<K, V>, Serializable {

    private List<V> list;
    private SortedMap<K, V> map;
    private Map<K, String> mapIndex;
    private List<K> listKey;
    private int index = -1;

    /**
     * Creates a new instance of MapList
     */
    public MapList() {
        list = new ArrayList<V>();
        map = new TreeMap<K, V>();
        mapIndex = new HashMap<K, String>();
        listKey = new ArrayList<K>();
    }

    /**
     * getList
     */
    public List<V> getList() {
        return list;
    }

    /**
     * getMap
     */
    public SortedMap<K, V> getMap() {
        return map;
    }

    @Override
    public Comparator<? super K> comparator() {
        return map.comparator();
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        this.index(toKey);
        return map.subMap(fromKey, toKey);
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return map.headMap(toKey);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return map.tailMap(fromKey);
    }

    @Override
    public K firstKey() {
        return map.firstKey();
    }

    @Override
    public K lastKey() {
        return map.lastKey();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey((K) key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue((V) value);
    }

    @Override
    public V get(Object key) {
        this.index(key);
        return map.get((K) key);
    }

    @Override
    public V put(K key, V value) {
        String idxStr = mapIndex.get(key);
        int idx = -1;
        if (idxStr != null && !idxStr.equals("")) {
            idx = Integer.parseInt(idxStr);
        }
        if (idx > -1) {
            list.set(idx, value);
            map.put(key, value);
        } else {
            list.add(value);
            map.put(key, value);
            listKey.add(key);
            mapIndex.put(key, index() + "");
        }
        return value;
    }

    @Override
    public V remove(Object key) {
        if (this.index(key) == -1) {
            return null;
        }
        list.remove(this.index(key));
        listKey.remove(this.index(key));
        mapIndex.remove((K) key);
        this.updateIndex(index + 1, -1);
        this.index(index);
        return map.remove((K) key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> t) {
        map.putAll(t);
        Iterator entries = t.entrySet().iterator();
        Map.Entry<? extends K, ? extends V> entry;
        while (entries.hasNext()) {
            entry = (Map.Entry<? extends K, ? extends V>) entries.next();
            if (this.getIndex(entry.getKey()) == -1) {
                list.add(entry.getValue());
                listKey.add(entry.getKey());
                mapIndex.put(listKey.get(this.index()), this.index() + "");
            } else {
                list.set(this.getIndex(entry.getKey()), entry.getValue());
            }
        }
    }

    @Override
    public void clear() {
        list.clear();
        listKey.clear();
        map.clear();
        mapIndex.clear();
        index = -1;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return list;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    public boolean contains(Object o) {
        return list.contains((V) o);
    }

    public Iterator<V> iterator() {
        return list.iterator();
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    public boolean add(V o) {
        K uuid = (K) Uuid.getInstance().toString();
        listKey.add(uuid);
        mapIndex.put(uuid, this.index() + "");
        map.put(uuid, o);
        return list.add(o);
    }

    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    public boolean addAll(Collection<? extends V> c) {
        return addAll(0, c);
    }

    public boolean addAll(int index, Collection<? extends V> c) {
        Iterator<? extends V> iter = c.iterator();
        while (iter.hasNext()) {
            add(index, iter.next());
        }
        return true;
    }

    public boolean removeAll(Collection<? extends K> c) {
        Iterator<? extends K> iter = c.iterator();
        while (iter.hasNext()) {
            K key = iter.next();
            this.remove(key);
        }
        return true;
    }

    public boolean retainAll(Collection<? extends K> c) {
        List<K> delList = new ArrayList<K>();
        Iterator<? extends K> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            K key = iter.next();
            if (!c.contains(key)) {
                delList.add(key);
            }
        }
        removeAll(delList);
        return true;
    }

    public V get(int index) {
        this.index(index);
        return list.get(index);
    }

    public V set(int index, V element) {
        this.index(index);
        return list.set(index, element);
    }

    public void add(int index, V element) {
        K uuid = (K) Uuid.getInstance().toString();
        listKey.add(index, uuid);
        mapIndex.put(uuid, index + "");
        list.add(index, element);
        map.put(uuid, element);
        this.updateIndex(index, 1);
        this.index(index);
    }

    public V remove(int index) {
        map.remove(listKey.get(index));
        mapIndex.remove(listKey.get(index));
        listKey.remove(index);
        this.updateIndex(index + 1, -1);
        this.index(index);
        return list.remove(index);
    }

    public int indexOf(Object o) {
        this.index(list.indexOf(o));
        return this.getIndex();
    }

    public int lastIndexOf(Object o) {
        this.index(list.lastIndexOf(o));
        return getIndex();
    }

    public ListIterator<V> listIterator() {
        return list.listIterator();
    }

    public ListIterator<V> listIterator(int index) {
        return list.listIterator(index);
    }

    public List<V> subList(int fromIndex, int toIndex) {
        this.index(toIndex);
        return list.subList(fromIndex, toIndex);
    }

    private void updateIndex(int index, int step) {
        Iterator<? extends K> iter = listKey.listIterator(index + step);
        while (iter.hasNext()) {
            K key = iter.next();
            mapIndex.put(key, (this.getIndex(key) + step) + "");
        }
    }

    /**
     * 设置当前索引为尾值，并返回
     */
    private int index() {
        index = listKey.size() - 1;
        return index;
    }

    /**
     * 设置当前索引，并返回
     */
    private int index(int index) {
        if (index >= listKey.size()) {
            index = this.index();
        }
        this.index = index;
        return this.index;
    }

    /**
     * 通过关键字设置当前操作索引，并返回
     */
    private int index(Object key) {
        index = getIndex(key);
        return index;
    }

    /**
     * 得到当前操作索引
     */
    public int getIndex() {
        return index;
    }

    /**
     * 通过关键字得到相应索引
     */
    public int getIndex(Object key) {
        String indexStr = mapIndex.get((K) key);
        if (indexStr == null || indexStr.equals("")) {
            return -1;
        }
        return Integer.parseInt(indexStr);
    }

    /**
     * 得到当前操作关键字
     */
    public K getKey() {
        return listKey.get(index);
    }

    /**
     * 得到指定索引对应的关键字
     */
    public K getKey(int index) {
        return listKey.get(index);
    }

    private void toStringPrint() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder str_buld = new StringBuilder();
        str_buld.append("\n\r{\n\r");
        for (int i = 0; i < list.size(); i++) {
            str_buld.append("[").append(i).append("]");
            str_buld.append("\t<K>:");
            str_buld.append(this.getKey(i));
            str_buld.append("\t<V>:");
            str_buld.append(this.get(i));
            str_buld.append(";\n\r");
        }
        str_buld.append("}");
        return str_buld.toString();
    }

    public static void test() {
        MapList<String, String> testMap = new MapList<String, String>();
        testMap.put("001", "WJB_A");
        testMap.put("002", "WJB_B");
        testMap.put("003", "WJB_C");
        testMap.put("004", "WJB_D");
        testMap.put("005", "WJB_E");
        testMap.add(2, "00000");
        testMap.add(6, "7777777");

        MapList<String, String> testMap2 = new MapList<String, String>();
        testMap2.put("001", "WJB_AAAAA");
        testMap2.put("005", "WJB_55555");
        testMap2.put("006", "WJB_66666");
        testMap2.put("007", "WJB_77777");

        //*
        System.out.println("\n\n--------------- putAll\n");
        testMap.putAll(testMap2);
        testMap.toStringPrint();
        //*/

        System.out.println("\n\n--------------- remove\n");
        testMap.remove(3);
        testMap.remove("006");
        testMap.toStringPrint();
        //*
        System.out.println("\n\n--------------- addAll\ny");
        testMap.addAll(testMap2.values());
        testMap.toStringPrint();

        MapList<String, String> testMap3 = new MapList<String, String>();
        testMap3.put("006", "WJB_66666");
        testMap3.put("007", "WJB_77777");
        MapList<String, String> testMap4 = new MapList<String, String>();
        testMap4.put("001", "WJB_A");

        System.out.println("\n\n--------------- removeAll\n");
        testMap.removeAll(testMap3.keySet());
        testMap.toStringPrint();

        System.out.println("\n\n--------------- retainAll\n");
        testMap.retainAll(testMap4.keySet());
        testMap.toStringPrint();
        //*/
    }
}
