package com.oopecommerce.utils.sorts;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SortableUtils {

    public static <T extends ISortable> List<T> sortByPosition(Collection<T> items, SortDirection direction)
            throws Exception {
        if (items == null) {
            throw new Exception("Items are not sortable");
        }
        switch (direction) {
            case ASC:
                return items.stream()
                        .sorted((a, b) -> Integer.compare(a.getPosition(), b.getPosition()))
                        .collect(Collectors.toList());
            case DESC:
                return items.stream()
                        .sorted((a, b) -> Integer.compare(b.getPosition(), a.getPosition()))
                        .collect(Collectors.toList());
            default:
                throw new Exception("Unsupported SortDirection");
        }
    }
}