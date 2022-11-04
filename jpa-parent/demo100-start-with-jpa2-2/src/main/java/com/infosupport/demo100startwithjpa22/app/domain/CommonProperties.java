package com.infosupport.demo100startwithjpa22.app.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class CommonProperties {
    @Id
    @GeneratedValue
    protected Long id;
    @Version
    protected Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommonProperties)) return false;

        CommonProperties that = (CommonProperties) o;

        if (!id.equals(that.id)) return false;
        return version.equals(that.version);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + version.hashCode();
        return result;
    }

}


