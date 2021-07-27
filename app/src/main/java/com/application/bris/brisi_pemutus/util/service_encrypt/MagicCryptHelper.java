package com.application.bris.brisi_pemutus.util.service_encrypt;

import com.application.bris.brisi_pemutus.BuildConfig;
import com.application.bris.brisi_pemutus.util.magiccrypt.MagicCrypt;

public class MagicCryptHelper {
    private String key = BuildConfig.SERVER_CRYPT;
    private MagicCrypt magicCrypt;

    public MagicCryptHelper()
    {
        magicCrypt = new MagicCrypt(this.key, 128);
    }

    public String encrypt(String message)
    {
        return magicCrypt.encrypt(message);
    }

    public String decrypt(String message)
    {
        return magicCrypt.decrypt(message);
    }
}
