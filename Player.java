class Player {
    String name;
    bool turn;
    char symbol;

    public Player(String name, bool turn, char symbol) {
        this.name = name;
        this.turn = turn;
        this.symbol = symbol;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public bool getTurn() {
        return this.turn;
    }

    public void setTurn(bool turn) {
        this.turn = turn;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

}