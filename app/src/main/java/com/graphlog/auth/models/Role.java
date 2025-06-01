package com.graphlog.auth.models;

import java.util.ArrayList;
import java.util.UUID;

public class Role {
    static public class Claim {
        UUID id;
        String name;

        public Claim(UUID id, String name){
            this.id = id;
            this.name = name;
        }
        
        public Boolean equals(Claim claim) {
            return this.id.equals(claim.id);
        }

		public UUID getId() {
			return id;
		}

		public String getName() {
			return name;
		}
    }

    UUID id;
    String name;
    ArrayList<Claim> claims;

    public Role(UUID id, String name, ArrayList<Claim> claims) {
        this.id = id;
        this.name = name;
        this.claims = claims;
    }

    public Boolean equals(Role role) {
        return this.id.equals(role.id);
    }

    public Boolean isAllowedTo(Claim claim) {
        for (Claim roleClaim : this.claims) {
            if (claim.equals(roleClaim)) {
                return true;
            }
        }
        return false;
    }

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Claim> getClaims() {
		return claims;
	}
}
