package com.mrlep.meon.utils;

import com.mrlep.meon.xlibrary.jwt.JwtRoleEntity;
import lombok.Data;

/**
 * Comment
 *
 * @author nguyenhungbk96@gmail.com
 */
@Data
public class TelecareUserEntity {
	Long telecareUserId;
	String keycloakUserId;
	String username;
	String password;
	String name;
	String role;
	String baseRole;
	String strJwtToken;
	String orgName;
	Long exp;
	Long iat;
	Long auth_time;
	JwtRoleEntity realm_access;
	JwtAccountEntity resource_access;
	String preferred_username;
	Boolean isVerifyToken;

	public Long getTelecareUserId() {
		return telecareUserId;
	}

	public void setTelecareUserId(Long telecareUserId) {
		this.telecareUserId = telecareUserId;
	}

	public String getKeycloakUserId() {
		return keycloakUserId;
	}

	public void setKeycloakUserId(String keycloakUserId) {
		this.keycloakUserId = keycloakUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBaseRole() {
		return baseRole;
	}

	public void setBaseRole(String baseRole) {
		this.baseRole = baseRole;
	}

	public String getStrJwtToken() {
		return strJwtToken;
	}

	public void setStrJwtToken(String strJwtToken) {
		this.strJwtToken = strJwtToken;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getExp() {
		return exp;
	}

	public void setExp(Long exp) {
		this.exp = exp;
	}

	public Long getIat() {
		return iat;
	}

	public void setIat(Long iat) {
		this.iat = iat;
	}

	public Long getAuth_time() {
		return auth_time;
	}

	public void setAuth_time(Long auth_time) {
		this.auth_time = auth_time;
	}

	public JwtRoleEntity getRealm_access() {
		return realm_access;
	}

	public void setRealm_access(JwtRoleEntity realm_access) {
		this.realm_access = realm_access;
	}

	public JwtAccountEntity getResource_access() {
		return resource_access;
	}

	public void setResource_access(JwtAccountEntity resource_access) {
		this.resource_access = resource_access;
	}

	public String getPreferred_username() {
		return preferred_username;
	}

	public void setPreferred_username(String preferred_username) {
		this.preferred_username = preferred_username;
	}

	public Boolean getVerifyToken() {
		return isVerifyToken;
	}

	public void setVerifyToken(Boolean verifyToken) {
		isVerifyToken = verifyToken;
	}

	private void hasPermisison() throws TeleCareException {
		boolean pms = (resource_access.telecare != null || resource_access.telecare.getRoles().size() > 0);
		if (!pms) {
			throw new TeleCareException("No role");
		}
	}

	public boolean isPatient() throws TeleCareException {
		hasPermisison();
		return resource_access.telecare.getRoles().contains(Role.TELECARE_CITIZEN.val());
	}

	public boolean isDoctor() throws TeleCareException {
		hasPermisison();
		return resource_access.telecare.getRoles().contains(Role.TELECARE_DOCTOR.val());
	}

	public boolean isNurs() throws TeleCareException {
		hasPermisison();
		return resource_access.telecare.getRoles().contains(Role.TELECARE_NURSING.val());
	}

	public boolean isClinic() throws TeleCareException {
		hasPermisison();
		return resource_access.telecare.getRoles().contains(Role.TELECARE_CLINIC.val());
	}

	public boolean isAdmin() throws TeleCareException {
		hasPermisison();
		return resource_access.telecare.getRoles().contains(Role.TELECARE_ADMIN.val());
	}

	public enum Role {
		TELECARE_DOCTOR("Telecare_Doctor"),
		TELECARE_NURSING("Telecare_Nursing"),
		TELECARE_CLINIC("Telecare_Clinic"),
		TELECARE_ADMIN("Telecare_Admin"),
		TELECARE_CITIZEN("Telecare_Citizen");

		private String role;

		private Role(String role) {
			this.role = role;
		}

		public String val() {
			return this.role;
		}
	}

	@Data
	public static class JwtAccountEntity {
		JwtRoleEntity account;
		JwtRoleEntity telecare;
	}
}
