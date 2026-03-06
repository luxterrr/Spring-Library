package com.kaizen.Library.domains.googlebook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class VolumeInfo {

    private String title;
    private List<String> authors;
    private ImageLinks imageLinks;
}
