package net.webspite.dataset.url.model;

import java.util.EnumSet;

public record Sort (String id, EnumSet<SortOption> options){
}
