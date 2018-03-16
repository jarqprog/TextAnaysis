package com.jarq;

import com.jarq.iterators.CharIterator;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticalAnalysis {

    private Iterator<String> iterator;
    private Map<String,Integer> dictionary;
    private List<String> dataCollection;

    public StatisticalAnalysis(Iterator<String> it) {
        iterator = it;
        dictionary = new HashMap<>();
        dataCollection = new ArrayList<>();
        setData();
    }

    public Integer countOf(String... elements) {
        return null;
    }

    public Integer dictionarySize() {
        return dictionary.size();
    }

    public Integer size() {
        return dataCollection.size();
    }

    public Set<String> occurMoreThan(Integer num) {
        Set<String> filtered = new TreeSet<>();
        for(Map.Entry<String,Integer> pair : dictionary.entrySet()) {
            if(pair.getValue() > num) {
                filtered.add(pair.getKey());
            }
        }
        return filtered;
    }

    private void setData() {
        while(iterator.hasNext()) {
            String element = iterator.next();
            dataCollection.add(element);
            if(dictionary.containsKey(element)){
                dictionary.put(element, dictionary.get(element)+1);
            } else {
                dictionary.put(element, 1);
            }
        }
    }

    public Map<String, Integer> getDictionary() {
        return dictionary;
    }

    public List<String> getDataCollection() {
        return dataCollection;
    }

    public Boolean hasCharIterator() {
        return iterator instanceof CharIterator;
    }
}
