package com.kaizen.Library.DTOS;

import java.util.List;

public record GoogleBookDTO(List<Item>items) {
    public record Item(VolumeInfo volumeInfo) {}
    public record VolumeInfo(String title, List<String> authors, String mainCategory) {}
}
