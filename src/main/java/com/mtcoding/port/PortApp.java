package com.mtcoding.port;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PortApp {
    public static void main(String[] args) {
        try {
            // 1. 공항 정보 다운로드 (완)
            PortRepository repo = new PortRepository();
            String portJson = repo.download("https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList?serviceKey=1d4b60644d4515082456e13d65d46448695cb8a300d92371e3147cbd27160160&_type=json");
            // System.out.println(1);

            // 2. PortDTO에 파싱 (완)
            Gson gson = new Gson();
            PortDTO port = gson.fromJson(portJson, PortDTO.class);
            // System.out.println(2);

            // 3. HashMap에 옮기기 (완)
            List<PortDTO.Item> list = port.getResponse().getBody().getItems().getItem();
            HashMap<String , String> map = new HashMap<>();

            for (PortDTO.Item item : list) {
                String key = item.getAirportNm(); // 무안
                String value = item.getAirportId(); // NAARKJB
                map.put(key, value);
            }
            // System.out.println(3);

            // map.put("무안","NAARKJB");
            // map.put("광주","NAARKJJ");

            // 4. 출발지(김해), 목적지(김포), 출발시간(20251220) 키보드로 입력 받기
            Scanner sc = new Scanner(System.in);
            System.out.println("출발지를 입력하세요.");
            for (PortDTO.Item item : list) {
                System.out.print(item.getAirportNm()+" ");
            }
            System.out.println();
            String dep = sc.nextLine();
            System.out.println();
            System.out.println("목적지를 입력하세요.");
            for (PortDTO.Item item : list) {
                System.out.print(item.getAirportNm()+" ");
            }
            System.out.println();
            String arr = sc.nextLine();
            System.out.println();
            System.out.println("출발날짜를 입력하세요.YYYYMMDD (예시 : 20251219)");
            String time = sc.nextLine();

            // 5. 해시맵에서 김해 -> value, 김포 -> value (완)
            String depId = map.get(dep);
            String arrId = map.get(arr);
            // System.out.println(5);

            // 6. 항공스케줄 주소 만들기 다운로드 (완)
            String site = "https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=1d4b60644d4515082456e13d65d46448695cb8a300d92371e3147cbd27160160&pageNo=1&numOfRows=10&_type=json&depAirportId="+depId+"&arrAirportId="+arrId+"&depPlandTime="+time;
            // System.out.println(6);

            // 7. 항공스케줄 다운로드 (완)
            String scheduleJson = repo.download(site);
            // System.out.println(7);

            // 8. ScheduleDTO에 파싱 (완)
            ScheduleDTO schedule = gson.fromJson(scheduleJson, ScheduleDTO.class);
            // System.out.println(8);

            // 9. 항공 스케줄 출력 (완)
            List<ScheduleDTO.Item> list2 = schedule.getResponse().getBody().getItems().getItem();
            for (ScheduleDTO.Item item : list2) {
                if(item.getEconomyCharge() == null) {
                    System.out.println("항공권이 없습니다.");
                    System.out.println();
                    continue;
                }
                System.out.println("출발지 : " + item.getDepAirportNm());
                System.out.println("도착지 : " + item.getArrAirportNm());
                System.out.println("출발시간 : " + item.getDepPlandTime());
                System.out.println("도착시간 : " + item.getArrPlandTime());
                System.out.println("이코노미가격 : " + item.getEconomyCharge());
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
