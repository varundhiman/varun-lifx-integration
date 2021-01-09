package com.gfs.driverApps.warRoomNotification.sonar.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "projectStatus")
public class SonarProjectStatus {

    @JsonProperty
    String status;

    @JsonProperty
    List<SonarCondition> conditions;

    @JsonProperty
    List<SonarPeriod> periods;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SonarCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<SonarCondition> conditions) {
        this.conditions = conditions;
    }

    public List<SonarPeriod> getPeriods() {
        return periods;
    }

    public void setPeriods(List<SonarPeriod> periods) {
        this.periods = periods;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
