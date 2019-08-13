/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.article.model.article;

import java.util.*;

public class ReconstructionsStatus implements Comparable<ReconstructionsStatus> {

    public enum SpecificDetails {

        // Determining availability 
        TO_BE_REQUESTED("To be requested", "Determining availability"),
        POSITIVE_RESPONSE("Positive response", "Determining availability"),
        INVITED("Invited", "Determining availability"),
        REMINDER("Reminder", "Determining availability"),
        PESTERING("Pestering", "Determining availability"),
        ULTIMATUM("Ultimatum", "Determining availability"),
        // Not available
        NO_CONTACT_INFORMATION("No contact information", "Not available"),
        NO_RESPONSE("No response", "Not available"),
        REQUEST_DECLINED("Request declined", "Not available"),
        LOST_DATA("Lost data", "Not available"),
        COMMUNICATION_STOPPED("Communication stopped", "Not available"),

        //Available
        IN_PROCESSING_PIPELINE("In processing pipeline", "Available"),
        IN_RELEASE("In release", "Available"),
        IN_REPOSITORY("In repository", "Available"),
        ON_HOLD("On hold", "Available");

        private final String details;
        private final String status;

        private SpecificDetails(String d, String s) {
            details = d;
            status = s;
        }

        public static SpecificDetails getSpecificDetails(String value) {
            for (SpecificDetails v : values()) {
                if (v.getDetails().equalsIgnoreCase(value)) {
                    return v;
                }
            }
            throw new IllegalArgumentException();
        }

        public String getDetails() {
            return this.details;
        }

        public static String getGlobalStatus(List<SpecificDetails> statusList) {
            if (statusList.contains(IN_PROCESSING_PIPELINE) 
                    || statusList.contains(IN_RELEASE)
                    || statusList.contains(ON_HOLD) 
                    || statusList.contains(IN_REPOSITORY)) {
                return "Available";
            } if (statusList.contains(TO_BE_REQUESTED)
                    || statusList.contains(POSITIVE_RESPONSE)
                    || statusList.contains(INVITED)
                    || statusList.contains(REMINDER)
                    || statusList.contains(PESTERING)
                    || statusList.contains(ULTIMATUM)) {
                return "Determining availability";
            } else {
                return "Not available";
            }

        }

        public String getStatus() {
            return this.status;
        }

        public static List<String> getStatus2DetailsStr(String status) {
            List<String> detailList = new ArrayList();
            for (SpecificDetails v : values()) {
                if (v.getStatus().equalsIgnoreCase(status)) {
                    detailList.add(v.toString());
                }
            }
            return detailList;
        }

        public static List<SpecificDetails> getStatus2Details(String status) {
            List<SpecificDetails> detailList = new ArrayList();
            for (SpecificDetails v : values()) {
                if (v.getStatus().equalsIgnoreCase(status)) {
                    detailList.add(v);
                }
            }
            return detailList;
        }

        public static List<String> getStatusList() {
            Set<String> detailSet = new HashSet();
            List<String> detailList = new ArrayList();
            for (SpecificDetails v : values()) {
                detailSet.add(v.getStatus());
            }
            detailList.addAll(detailSet);
            return detailList;
        }

        public static List<String> getDetailsList() {
            List<String> detailList = new ArrayList();
            for (SpecificDetails v : values()) {
                detailList.add(v.getDetails());
            }
            return detailList;
        }
    }

    private Integer index;
    private SpecificDetails specificDetails;
    private Date date;
    private Date expirationDate;
    private Double nReconstructions;

    public ReconstructionsStatus() {
    }

    public ReconstructionsStatus(Double nReconstructions){
        this.nReconstructions = nReconstructions;
        this.date = new Date();
        this.specificDetails = SpecificDetails.TO_BE_REQUESTED;
        this.expirationDate = new Date();
        this.index = 1;
    }
    
    public ReconstructionsStatus(String specificDetails, Date expirationDate, Double nReconstructions) {
        this.date = new Date();
        this.specificDetails = SpecificDetails.getSpecificDetails(specificDetails);
        this.expirationDate = expirationDate;
        if (nReconstructions == null) {
            this.nReconstructions = 1D;
        } else {
            this.nReconstructions = nReconstructions;
        }
        if (this.expirationDate == null) {
            switch (this.specificDetails) {
                case POSITIVE_RESPONSE:
                    this.expirationDate = this.addDays(14);
                    break;
                case TO_BE_REQUESTED:
                    this.expirationDate = new Date();
                    break;
                case INVITED:
                case REMINDER:
                case PESTERING:
                case ULTIMATUM:
                    this.expirationDate = this.addDays(1);
                    break;
                case NO_CONTACT_INFORMATION:
                case NO_RESPONSE:
                case REQUEST_DECLINED:
                case LOST_DATA:
                case IN_PROCESSING_PIPELINE:
                case IN_RELEASE:
                case IN_REPOSITORY:
                case COMMUNICATION_STOPPED:
                    this.expirationDate = null;
                    break;
                default:
                    break;
            }

        }

    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
    
    public SpecificDetails getSpecificDetails() {
        return specificDetails;
    }

    public Date getDate() {
        return date;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public int compareTo(ReconstructionsStatus o) {
        return getDate().compareTo(o.getDate());
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSpecificDetails(SpecificDetails specificDetails) {
        this.specificDetails = specificDetails;
    }

    public Double getNReconstructions() {
        return nReconstructions;
    }

    public void setNReconstructions(Double nReconstructions) {
        this.nReconstructions = nReconstructions;
    }

    public Boolean is2BeRequested() {
        return this.specificDetails.equals(SpecificDetails.TO_BE_REQUESTED);
    }

    public Boolean isInitialized() {
        return this.specificDetails != null;
    }

    private Date addDays(Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    private Date addMonths(Integer months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReconstructionsStatus other = (ReconstructionsStatus) obj;

        if (!Objects.equals(this.index, other.index)) {
            return false;
        }
        if (this.specificDetails != other.specificDetails) {
            return false;
        }
        if (!isSameDay(this.expirationDate, other.expirationDate)) {
            return false;
        }
        if (!Objects.equals(this.nReconstructions, other.nReconstructions)) {
            return false;
        }
        return true;
    }

    private Boolean isSameDay(Date date1, Date date2) {
        Boolean sameDay = Boolean.FALSE;
        if (date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date1);
            cal2.setTime(date2);
            sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                    && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        } else if ((date1 == null && date2 == null)) {
            sameDay = Boolean.TRUE;
        }
        return sameDay;
    }
    

}
