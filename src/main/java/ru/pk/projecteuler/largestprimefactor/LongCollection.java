package ru.pk.projecteuler.largestprimefactor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class LongCollection {
    private long size = 0;
    private Element first;
    private Element last;

    public void add(long value) {
        Element el = new Element(value);
        if (first == null) {
            first = el;
            last = first;
        } else {
            last.setNext(el);
        }
        last = el;
        size++;
    }

    public long size() {
        return size;
    }

    public Element getFirst() {
        return first;
    }

    public Element getLast() {
        return last;
    }

    public Collection<Long> getCollection() {
        if (size > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("Слишком много значение для выгрузки в коллекцию");
        }

        Collection<Long> result = new ArrayList<>((int)size);
        Iterator it = iterator();
        while (it.hasNext()) {
            result.add(it.next());
        }
        return result;
    }

    public Iterator iterator() {
        return new Iterator(this);
    }

    private static class Element {
        private long value;
        private Element next = null;

        public Element(long value) {
            this.value = value;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public Element getNext() {
            return next;
        }

        public long getValue() {
            return value;
        }
    }

    public static class Iterator {
        private final LongCollection collection;
        private Element first;
        private Element current;

        public Iterator(LongCollection collection) {
            this.collection = collection;
            this.first = collection.getFirst();
            this.current = null;
        }

        public boolean hasNext() {
            if (current == null) {
                return first != null;
            } else {
                return current.getNext() != null;
            }
        }

        public long next() {
            if (current == null) {
                if (first == null) {
                    throw new NoSuchElementException();
                } else {
                    current = first;
                }
            } else {
                if (current.getNext() == null) {
                    throw new NoSuchElementException();
                } else {
                    current = current.getNext();
                }
            }

            return current.getValue();
        }

        public void reset() {
            first = collection.getFirst();
            current = null;
        }
    }
}
