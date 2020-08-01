package xyz.wongs.drunktard.domain.addbook.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RegUserExtExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RegUserExtExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andURegUserExtIdIsNull() {
            addCriterion("U_REG_USER_EXT_ID is null");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdIsNotNull() {
            addCriterion("U_REG_USER_EXT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdEqualTo(Long value) {
            addCriterion("U_REG_USER_EXT_ID =", value, "uRegUserExtId");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdNotEqualTo(Long value) {
            addCriterion("U_REG_USER_EXT_ID <>", value, "uRegUserExtId");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdGreaterThan(Long value) {
            addCriterion("U_REG_USER_EXT_ID >", value, "uRegUserExtId");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdGreaterThanOrEqualTo(Long value) {
            addCriterion("U_REG_USER_EXT_ID >=", value, "uRegUserExtId");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdLessThan(Long value) {
            addCriterion("U_REG_USER_EXT_ID <", value, "uRegUserExtId");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdLessThanOrEqualTo(Long value) {
            addCriterion("U_REG_USER_EXT_ID <=", value, "uRegUserExtId");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdIn(List<Long> values) {
            addCriterion("U_REG_USER_EXT_ID in", values, "uRegUserExtId");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdNotIn(List<Long> values) {
            addCriterion("U_REG_USER_EXT_ID not in", values, "uRegUserExtId");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdBetween(Long value1, Long value2) {
            addCriterion("U_REG_USER_EXT_ID between", value1, value2, "uRegUserExtId");
            return (Criteria) this;
        }

        public Criteria andURegUserExtIdNotBetween(Long value1, Long value2) {
            addCriterion("U_REG_USER_EXT_ID not between", value1, value2, "uRegUserExtId");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdIsNull() {
            addCriterion("DICT_REGION_ID is null");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdIsNotNull() {
            addCriterion("DICT_REGION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdEqualTo(Long value) {
            addCriterion("DICT_REGION_ID =", value, "dictRegionId");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdNotEqualTo(Long value) {
            addCriterion("DICT_REGION_ID <>", value, "dictRegionId");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdGreaterThan(Long value) {
            addCriterion("DICT_REGION_ID >", value, "dictRegionId");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DICT_REGION_ID >=", value, "dictRegionId");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdLessThan(Long value) {
            addCriterion("DICT_REGION_ID <", value, "dictRegionId");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdLessThanOrEqualTo(Long value) {
            addCriterion("DICT_REGION_ID <=", value, "dictRegionId");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdIn(List<Long> values) {
            addCriterion("DICT_REGION_ID in", values, "dictRegionId");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdNotIn(List<Long> values) {
            addCriterion("DICT_REGION_ID not in", values, "dictRegionId");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdBetween(Long value1, Long value2) {
            addCriterion("DICT_REGION_ID between", value1, value2, "dictRegionId");
            return (Criteria) this;
        }

        public Criteria andDictRegionIdNotBetween(Long value1, Long value2) {
            addCriterion("DICT_REGION_ID not between", value1, value2, "dictRegionId");
            return (Criteria) this;
        }

        public Criteria andUIdIsNull() {
            addCriterion("U_ID is null");
            return (Criteria) this;
        }

        public Criteria andUIdIsNotNull() {
            addCriterion("U_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUIdEqualTo(Long value) {
            addCriterion("U_ID =", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotEqualTo(Long value) {
            addCriterion("U_ID <>", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdGreaterThan(Long value) {
            addCriterion("U_ID >", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdGreaterThanOrEqualTo(Long value) {
            addCriterion("U_ID >=", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLessThan(Long value) {
            addCriterion("U_ID <", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLessThanOrEqualTo(Long value) {
            addCriterion("U_ID <=", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdIn(List<Long> values) {
            addCriterion("U_ID in", values, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotIn(List<Long> values) {
            addCriterion("U_ID not in", values, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdBetween(Long value1, Long value2) {
            addCriterion("U_ID between", value1, value2, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotBetween(Long value1, Long value2) {
            addCriterion("U_ID not between", value1, value2, "uId");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("GENDER is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("GENDER is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("GENDER =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("GENDER <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("GENDER >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("GENDER >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("GENDER <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("GENDER <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("GENDER like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("GENDER not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("GENDER in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("GENDER not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("GENDER between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("GENDER not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("BIRTHDAY is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("BIRTHDAY is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("BIRTHDAY =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("BIRTHDAY <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("BIRTHDAY >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BIRTHDAY >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("BIRTHDAY <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BIRTHDAY <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("BIRTHDAY in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("BIRTHDAY not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BIRTHDAY between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BIRTHDAY not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateIsNull() {
            addCriterion("LAST_ACTIVE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateIsNotNull() {
            addCriterion("LAST_ACTIVE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateEqualTo(Date value) {
            addCriterion("LAST_ACTIVE_DATE =", value, "lastActiveDate");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateNotEqualTo(Date value) {
            addCriterion("LAST_ACTIVE_DATE <>", value, "lastActiveDate");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateGreaterThan(Date value) {
            addCriterion("LAST_ACTIVE_DATE >", value, "lastActiveDate");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_ACTIVE_DATE >=", value, "lastActiveDate");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateLessThan(Date value) {
            addCriterion("LAST_ACTIVE_DATE <", value, "lastActiveDate");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateLessThanOrEqualTo(Date value) {
            addCriterion("LAST_ACTIVE_DATE <=", value, "lastActiveDate");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateIn(List<Date> values) {
            addCriterion("LAST_ACTIVE_DATE in", values, "lastActiveDate");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateNotIn(List<Date> values) {
            addCriterion("LAST_ACTIVE_DATE not in", values, "lastActiveDate");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateBetween(Date value1, Date value2) {
            addCriterion("LAST_ACTIVE_DATE between", value1, value2, "lastActiveDate");
            return (Criteria) this;
        }

        public Criteria andLastActiveDateNotBetween(Date value1, Date value2) {
            addCriterion("LAST_ACTIVE_DATE not between", value1, value2, "lastActiveDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}