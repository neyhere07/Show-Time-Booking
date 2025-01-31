package com.project.ShowTimeBooking.Enums;

public enum Role {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");
        private final String displayName;

        Role(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
}
