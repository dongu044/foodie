package main.foodie.domain.board;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(Visibility.class)
public class VisibilityTypeHandler extends BaseTypeHandler<Visibility> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Visibility parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(i, parameter.getCode());
  }

  @Override
  public Visibility getNullableResult(ResultSet rs, String columnName) throws SQLException {
    int code = rs.getInt(columnName);
    return rs.wasNull() ? null : Visibility.fromCode(code);
  }

  @Override
  public Visibility getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    int code = rs.getInt(columnIndex);
    return rs.wasNull() ? null : Visibility.fromCode(code);
  }

  @Override
  public Visibility getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    int code = cs.getInt(columnIndex);
    return cs.wasNull() ? null : Visibility.fromCode(code);
  }
}

