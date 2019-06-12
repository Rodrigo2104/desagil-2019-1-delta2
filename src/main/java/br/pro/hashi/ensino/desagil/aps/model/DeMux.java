package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    NandGate nandLeft;
    NandGate nandRight;
    NandGate nandLeft2;
    NandGate nandRight2;
    NandGate nandFinal;

    public DeMux() {
        super("DeMux", 2, 2);
        nandLeft = new NandGate();
        nandRight = new NandGate();
        nandLeft2 = new NandGate();
        nandRight2 = new NandGate();
        nandFinal = new NandGate();

        nandRight.connect(0, nandLeft);
        nandRight.connect(1,nandLeft);

        nandRight2.connect(1,nandLeft2);

        nandFinal.connect(0,nandRight2);
        nandFinal.connect(1,nandRight2);
    }


    @Override
    public boolean read(int outputPin) {
        switch (outputPin) {
            case 0:
//                return nandRight2.read();
                return nandFinal.read();

            case 1:
                return nandRight.read();

            default:
                throw new IndexOutOfBoundsException(outputPin);
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandRight2.connect(0, emitter);
                nandLeft.connect(0, emitter);

                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandLeft2.connect(0, emitter);
                nandLeft2.connect(1, emitter);

                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
