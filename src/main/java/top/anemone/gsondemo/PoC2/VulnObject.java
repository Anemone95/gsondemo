package top.anemone.gsondemo.PoC2;

import java.io.IOException;

public class VulnObject {
    private String cmd;
    public VulnObject() {}

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public int hashCode() {
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int hash=0;
        for (char c: cmd.toCharArray()) {
            hash^=c;
        }
        return hash;
    }
}
