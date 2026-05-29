package bankapp.progetto20242025piragine.model;

import java.sql.Timestamp;

public class Block {

    private int idBlock;
    private int blocker;   // user che blocca
    private int block;     // user bloccato
    private Timestamp dataBlocco;

    // 🔹 Costruttore vuoto
    public Block() {}

    // 🔹 Getter & Setter
    public int getIdBlock() {
        return idBlock;
    }

    public void setIdBlock(int idBlock) {
        this.idBlock = idBlock;
    }

    public int getBlocker() {
        return blocker;
    }

    public void setBlocker(int blocker) {
        this.blocker = blocker;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public Timestamp getDataBlocco() {
        return dataBlocco;
    }

    public void setDataBlocco(Timestamp dataBlocco) {
        this.dataBlocco = dataBlocco;
    }
}
