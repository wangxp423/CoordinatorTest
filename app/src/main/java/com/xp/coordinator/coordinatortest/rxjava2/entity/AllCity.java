package com.xp.coordinator.coordinatortest.rxjava2.entity;

import java.util.ArrayList;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2018/1/4 0004 10:11
 * @修改人：
 * @修改时间：2018/1/4 0004 10:11
 * @修改备注：
 */

public class AllCity {
    private String error_code;
    private String reason;
    private String resultcode;
    private ArrayList<City> result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public ArrayList<City> getResult() {
        return result;
    }

    public void setResult(ArrayList<City> result) {
        this.result = result;
    }

    public static class City {
        private String id;
        private String province;
        private String city;
        private String district;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "id = " + id + " province = " + province + " district = " + district;
        }
    }

    public ArrayList<City> getCitys() {
        ArrayList<City> citys = new ArrayList<>();
        City city1 = new City();
        city1.setId("1");
        city1.setProvince("北京");
        city1.setDistrict("北京");
        citys.add(city1);

        City city2 = new City();
        city2.setId("2");
        city2.setProvince("北京");
        city2.setDistrict("海淀");
        citys.add(city2);

        City city3 = new City();
        city3.setId("3");
        city3.setProvince("北京");
        city3.setDistrict("朝阳");
        citys.add(city3);

        City city4 = new City();
        city4.setId("4");
        city4.setProvince("北京");
        city4.setDistrict("顺义");
        citys.add(city4);

        City city5 = new City();
        city5.setId("5");
        city5.setProvince("北京");
        city5.setDistrict("昌平");
        citys.add(city5);

        return citys;
    }
}
