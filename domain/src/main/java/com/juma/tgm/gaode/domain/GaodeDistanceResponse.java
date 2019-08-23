package com.juma.tgm.gaode.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GaodeDistanceResponse implements Serializable {


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8766464607916574199L;

    private int status;
    
    private String info;
    
    private List<Result> results = new ArrayList<Result>();
    
    public class Result implements Serializable {
        

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = -7733184847378164136L;

        private int origin_id;
        
        private int dest_id;
        
        private long distance;//米
        
        private long duration;//秒
        
        private String info;
        
        private int code;

        public int getOrigin_id() {
            return origin_id;
        }

        public void setOrigin_id(int origin_id) {
            this.origin_id = origin_id;
        }

        public int getDest_id() {
            return dest_id;
        }

        public void setDest_id(int dest_id) {
            this.dest_id = dest_id;
        }

        public long getDistance() {
            return distance;
        }

        public void setDistance(long distance) {
            this.distance = distance;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "Result [origin_id=" + origin_id + ", dest_id=" + dest_id + ", distance=" + distance + ", duration=" + duration + ", info=" + info + ", code=" + code + "]";
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GaodeDistanceResponse [status=" + status + ", info=" + info + ", results=" + results + "]";
    }
    
}
