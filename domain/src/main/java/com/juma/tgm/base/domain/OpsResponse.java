package com.juma.tgm.base.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OpsResponse implements Serializable {
    private String message;
    private List<Meta> metas;
    private List<Object[]> datas;

    public class Meta implements Serializable {
        private String name;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
