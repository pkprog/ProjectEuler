package ru.pk.projecteuler.largestprimefactor;

public class LongCollection {
    private long size;
    private Element first;
    private Element last;

    public void add(long value) {
        Element el = new Element(value);
        if (first == null) {
            first = el;
        } else {
            first.setNext(el);
        }
        last = el;
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
}
