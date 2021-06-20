package com.fid.demo.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BaseDateComparableDto extends BaseDto implements Comparable<BaseDateComparableDto> {

    private LocalDate date;
    private String className;

    public BaseDateComparableDto(Integer id, LocalDate date, String className) {
        super(id);
        this.date = date;
        this.className = className;
    }

    @Override
    public int compareTo(@NotNull BaseDateComparableDto o) {
        if (date == null) {
            return 1;
        }
        if (o.getDate() == null) {
            return -1;
        }
        return o.getDate().compareTo(date);
    }
}
