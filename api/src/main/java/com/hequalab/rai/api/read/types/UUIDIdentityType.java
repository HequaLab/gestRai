package com.hequalab.rai.api.read.types;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import com.hequalab.rai.dddd.UUIDIdentity;

public abstract class UUIDIdentityType<T extends UUIDIdentity> implements UserType {
	
	private final Class<T> type;
	
    public UUIDIdentityType(Class<T> type) {
		this.type = type;
	}

	public Object assemble(Serializable cached, Object owner) {
        return cached;
    }

    public Object deepCopy(Object value) {
        if (value==null)
            return null;
        if (!(value.getClass().equals(type)))
            throw new UnsupportedOperationException("can't convert "+ value.getClass());
        return create(value.toString());
    }

    public Serializable disassemble(Object value) throws HibernateException {
        if (!(value instanceof java.lang.String))
            throw new UnsupportedOperationException("can't convert "+value.getClass());
        return create(value.toString());
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        return x == null ? false : x.equals(y);
    }

    public int hashCode(Object value) throws HibernateException {
        return value.hashCode();
    }

    public boolean isMutable() {
        return false;
    }

    public Object replace(Object original, Object target, Object owner)  {
        return original;
    }

    @SuppressWarnings("rawtypes")
	public Class returnedClass() {
        return type;
    }

    public int[] sqlTypes() {
        return new int[] {Types.VARCHAR};
    }

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
        String value = rs.getString(names[0]);
        if (value==null)
            return null;        
        return create(value);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
        if (value==null) {
            st.setNull(index, Types.VARCHAR);
            return;
        }
        if (!(value.getClass().equals(type)))
            throw new UnsupportedOperationException("can't convert " + value.getClass());
        st.setString( index, value.toString());
		
	}
	
	private T create(String value) {
		try {
			return type.getConstructor(String.class).newInstance(value);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}