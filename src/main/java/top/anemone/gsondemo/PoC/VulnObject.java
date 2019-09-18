package top.anemone.gsondemo.PoC;

import java.io.IOException;

public class VulnObject {
    private String cmd;
    public VulnObject() {}
    public String getCmd() { return cmd; }
    public void setCmd(String cmd) { this.cmd = cmd; }

    @Override
    public String toString(){
        try { Runtime.getRuntime().exec(cmd); }
        catch (IOException e) { e.printStackTrace(); }
        return cmd;
    }

    @Override
    public int hashCode() {
        int hash=0;
        for (char c: cmd.toCharArray()) { hash^=c;}
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}