package com.yang.demospring.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 在 Money 与 Long 之间转换的 TypeHandler，处理 CNY 人民币,需要在yml文件里面配置 type-handlers-package: com.yang.demospring.handler
 */
@Slf4j
public class MoneyTypeHandler extends BaseTypeHandler<Money> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Money parameter, JdbcType jdbcType) throws SQLException {
        log.error("setNonNullParameter======"+ps+"--"+i+"--"+parameter+"--"+jdbcType);
        ps.setLong(i, parameter.getAmountMinorLong());
}

    @Override
    public Money getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        log.info("getNullableResult======"+rs.toString()+"--"+columnName);
        return parseMoney(rs.getLong(columnName));
    }

    @Override
    public Money getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseMoney(rs.getLong(columnIndex));
    }

    @Override
    public Money getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseMoney(cs.getLong(columnIndex));
    }

    private Money parseMoney(Long value) {
//        log.info("value===="+value);
        return Money.of(CurrencyUnit.of("CNY"), value / 100.0);
    }
}
