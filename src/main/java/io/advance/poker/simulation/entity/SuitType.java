package io.advance.poker.simulation.entity;

public enum SuitType {

	CLUBS(0x8000),
	DIAMONDS(0x4000),
	HEARTS(0x2000),
	SPADES(0x1000);

	private int value;
	SuitType(int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
