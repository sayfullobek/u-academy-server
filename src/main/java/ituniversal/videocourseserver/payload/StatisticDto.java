package ituniversal.videocourseserver.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticDto {
    private Integer courseSize;
    private Integer myCodeSize;
    private Integer moduleSize;
    private Integer commentSize;
    private Integer authSize;

}
