package ch.bfh.ti.sed.patmon1.server.model;

import java.math.BigDecimal;
import java.util.Date;

public class Measurement {

	private BigDecimal temperature;

	private Date timestamp;

	/**
	 * @return id of object
	 */
	public BigDecimal getTemperature() {
		return temperature;
	}

	/**
	 * @return timestamp of measurement
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		// generated
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((temperature == null) ? 0 : temperature.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// generated
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Measurement other = (Measurement) obj;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}
