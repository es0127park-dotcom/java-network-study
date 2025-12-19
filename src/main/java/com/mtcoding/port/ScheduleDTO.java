package com.mtcoding.port;

/**
 * https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=1d4b60644d4515082456e13d65d46448695cb8a300d92371e3147cbd27160160&pageNo=1&numOfRows=10&_type=json&depAirportId=NAARKJJ&arrAirportId=NAARKPC&depPlandTime=20251220
 */

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScheduleDTO {

    private Response response;

    @Getter
    @Setter
    public static class Response {
        private Header header;
        private Body body;
    }

    @Getter
    @Setter
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @Setter
    public static class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Getter
    @Setter
    public static class Items {
        private List<Item> item;
    }

    @Getter
    @Setter
    public static class Item {
        private String airlineNm;
        private String arrAirportNm;
        private long arrPlandTime;
        private String depAirportNm;
        private long depPlandTime;
        private Integer economyCharge;
        private Integer prestigeCharge;
        private String vihicleId;
    }
}
