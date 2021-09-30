package com.alemal.validation;

public class EmailValidator {
    private final static String VALID_CHARACTERS = "!#$%&'*+-/=?^_`{|}~";

    public boolean validate(String email) {
        return isNotEmpty(email)
                && hasEtaSign(email)
                && hasValidLocalPath(email)
                && hasValidDomain(email)
                && hasValidTLD(email);
    }

    private boolean isNotEmpty(String email) {
        return email != null && !email.isBlank();
    }

    private boolean hasEtaSign(String email) {
        long count = email.chars()
                .filter(ch -> ch == '@')
                .count();

        return count == 1;
    }

    private boolean hasValidLocalPath(String email) {
        var localPathStr = email.substring(0, email.indexOf("@"));
        var localPath = localPathStr.toCharArray();

        for (var symbol : localPath) {
            if (!Character.isLetterOrDigit(symbol)
                    && symbol != '.'
                    && !VALID_CHARACTERS.contains( String.valueOf(symbol) )
            )
                return false;
        }

        return 0 < localPath.length && localPath.length < 64
                && localPath[0] != '.'
                && localPath[localPath.length - 1] != '.'
                && !localPathStr.contains("..");
    }

    private boolean hasValidDomain(String email) {
        var domainStr = email.substring(email.indexOf("@") + 1);
        var domain = domainStr.split("\\.");

        if (domainStr.contains("..")) {
            return false;
        }

        for (int i = 0; i < domain.length - 1; i++)
        {
            var hostname = domain[i].toCharArray();

            for (var symbol : hostname) {
                if (!Character.isLetterOrDigit(symbol)
                        && symbol != '.'
                        && symbol != '-'
                )
                    return false;
            }

            if (hostname[0] == '-' || hostname[hostname.length-1] == '-') {
                return false;
            }
        }

        return true;
    }

    private boolean hasValidTLD(String email) {
        var tldService = new TLDService();
        var tld = email
                .substring(email.lastIndexOf(".") + 1)
                .toUpperCase();

        return tldService.validateTLD(tld);
    }
}
