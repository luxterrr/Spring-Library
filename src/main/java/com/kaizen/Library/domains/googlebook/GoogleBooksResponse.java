package com.kaizen.Library.domains.googlebook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GoogleBooksResponse {

    private List<GoogleBookItem> googleBookItems;
}
