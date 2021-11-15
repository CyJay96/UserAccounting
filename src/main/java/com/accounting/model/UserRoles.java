package com.accounting.model;

public enum UserRoles {

    // USER (lv1), CUSTOMER(lv1), ADMIN (lv2), PROVIDER(lv2), SUPER_ADMIN(lv3)

    USER {
        @Override
        public int getLevel() {
            return 1;
        }
    },
    CUSTOMER {
        @Override
        public int getLevel() {
            return 1;
        }
    },
    ADMIN {
        @Override
        public int getLevel() {
            return 2;
        }
    },
    PROVIDER {
        @Override
        public int getLevel() {
            return 2;
        }
    },
    SUPER_ADMIN {
        @Override
        public int getLevel() {
            return 3;
        }
    };

    public abstract int getLevel();

    public static String getNameById(int id) {
        for (UserRoles role : values()) {
            if (role.ordinal() == id) {
                return role.name();
            }
        }

        return "";
    }

    public static String getAllRoles() {
        String result = "";
        int i = 1;
        for (UserRoles role : values()) {
            result += i++ + "-" + role.name() + " ";
        }

        return result;
    }

}
