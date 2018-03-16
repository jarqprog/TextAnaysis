package com.jarq.model;

import com.jarq.iterators.CharIterator;

import java.util.*;

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
        Integer counter = 0;
        for(String element : elements) {
            if(dictionary.containsKey(element)) {
                counter += dictionary.get(element);
            }
        }
        return counter;
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

    public Boolean hasCharIterator() {
        return iterator instanceof CharIterator;
    }
}
