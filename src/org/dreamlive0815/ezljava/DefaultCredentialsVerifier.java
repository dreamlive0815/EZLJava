package org.dreamlive0815.ezljava;

public class DefaultCredentialsVerifier extends CredentialsVerifier
{
    @Override
    public void Verify(Credentials c) throws Exception
    {
        String up = String.format("%s %s", c.username, c.password);
        if(HashUtil.Verify("b62ca243a268c5e8cccee2461e3c59f546ae8e4b", up))
        {
            if(!HashUtil.Verify("7712423ea15b425601a6984647e9a543b1bd2bff", c.macAddress))
                throw new Exception(T.G("DCV.V.MNM"));
        }
    }
}