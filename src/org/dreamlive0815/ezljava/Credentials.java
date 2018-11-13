package org.dreamlive0815.ezljava;

public class Credentials
{
    public String username;
    public String password;
    public String macAddress;
    public String devName;
}

abstract class CredentialsVerifier
{
    public abstract void Verify(Credentials c) throws Exception;
}