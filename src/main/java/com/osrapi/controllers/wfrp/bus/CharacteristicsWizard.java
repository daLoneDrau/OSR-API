package com.osrapi.controllers.wfrp.bus;

public class CharacteristicsWizard {
    /**
     * Gets the fate points value from the starting fate points table.
     * @param race the character race id
     * @param roll the roll
     * @return {@link int}
     */
    private int getFatePoints(final int race, final int roll) {
        int wounds = 0;
        switch (roll) {
        case 1:
        case 2:
        case 3:
        case 4:
            switch (race) {
            case 0:// DWARF
                wounds = 1;
                break;
            case 1:// ELF
                wounds = 1;
                break;
            case 2:// HALFLING
                wounds = 2;
                break;
            case 3:// HUMAN
                wounds = 2;
                break;
            }
            break;
        case 5:
        case 6:
        case 7:
            switch (race) {
            case 0:// DWARF
                wounds = 2;
                break;
            case 1:// ELF
                wounds = 2;
                break;
            case 2:// HALFLING
                wounds = 2;
                break;
            case 3:// HUMAN
                wounds = 3;
                break;
            }
            break;
        case 8:
        case 9:
        case 10:
            switch (race) {
            case 0:// DWARF
                wounds = 3;
                break;
            case 1:// ELF
                wounds = 2;
                break;
            case 2:// HALFLING
                wounds = 3;
                break;
            case 3:// HUMAN
                wounds = 3;
                break;
            }
            break;
        }
        return wounds;
    }
    /**
     * Gets the wounds value from the starting wounds table.
     * @param race the character race id
     * @param roll the roll
     * @return {@link int}
     *//*
       * private int getWounds(final int race, final int roll) { int wounds = 0;
       * switch (roll) { case 1: case 2: case 3: switch (race) { case 0:// DWARF
       * wounds = 11; break; case 1:// ELF wounds = 9; break; case 2:// HALFLING
       * wounds = 8; break; case 3:// HUMAN wounds = 10; break; } break; case 4:
       * case 5: case 6: switch (race) { case 0:// DWARF wounds = 12; break;
       * case 1:// ELF wounds = 10; break; case 2:// HALFLING wounds = 9; break;
       * case 3:// HUMAN wounds = 11; break; } break; case 7: case 8: case 9:
       * switch (race) { case 0:// DWARF wounds = 13; break; case 1:// ELF
       * wounds = 11; break; case 2:// HALFLING wounds = 10; break; case 3://
       * HUMAN wounds = 12; break; } break; case 10: switch (race) { case 0://
       * DWARF wounds = 14; break; case 1:// ELF wounds = 12; break; case 2://
       * HALFLING wounds = 11; break; case 3:// HUMAN wounds = 13; break; }
       * break; } return wounds; } public void setCharacteristics(final
       * WFRPCharacterEntity entity) {
       * System.out.println("CharacteristicsWizard.setCharacteristics("+entity
       * .getRace().getId()); final int ten = 10, twenty = 20, thirty = 30;
       * switch ((int) entity.getRace().getId()) { case 0: // DWARF
       * entity.setWeaponSkill( Diceroller.getInstance().rollXdY(2, ten) +
       * thirty); entity.setBallisticSkill( Diceroller.getInstance().rollXdY(2,
       * ten) + twenty); entity.setStrength( Diceroller.getInstance().rollXdY(2,
       * ten) + twenty); entity.setToughness(
       * Diceroller.getInstance().rollXdY(2, ten) + thirty);
       * entity.setAgility(Diceroller.getInstance().rollXdY(2, ten) + ten);
       * entity.setIntelligence( Diceroller.getInstance().rollXdY(2, ten) +
       * twenty); entity.setWillPower( Diceroller.getInstance().rollXdY(2, ten)
       * + twenty); entity.setFellowship( Diceroller.getInstance().rollXdY(2,
       * ten) + ten); entity.setMovement(3); break; case 1: // ELF
       * entity.setWeaponSkill( Diceroller.getInstance().rollXdY(2, ten) +
       * twenty); entity.setBallisticSkill( Diceroller.getInstance().rollXdY(2,
       * ten) + thirty); entity.setStrength( Diceroller.getInstance().rollXdY(2,
       * ten) + twenty); entity.setToughness(
       * Diceroller.getInstance().rollXdY(2, ten) + twenty); entity.setAgility(
       * Diceroller.getInstance().rollXdY(2, ten) + thirty);
       * entity.setIntelligence( Diceroller.getInstance().rollXdY(2, ten) +
       * twenty); entity.setWillPower( Diceroller.getInstance().rollXdY(2, ten)
       * + twenty); entity.setFellowship( Diceroller.getInstance().rollXdY(2,
       * ten) + twenty); entity.setMovement(5); break; case 2: // HALFLING
       * entity.setWeaponSkill( Diceroller.getInstance().rollXdY(2, ten) + ten);
       * entity.setBallisticSkill( Diceroller.getInstance().rollXdY(2, ten) +
       * thirty); entity.setStrength( Diceroller.getInstance().rollXdY(2, ten) +
       * ten); entity.setToughness( Diceroller.getInstance().rollXdY(2, ten) +
       * ten); entity.setAgility( Diceroller.getInstance().rollXdY(2, ten) +
       * thirty); entity.setIntelligence( Diceroller.getInstance().rollXdY(2,
       * ten) + twenty); entity.setWillPower(
       * Diceroller.getInstance().rollXdY(2, ten) + twenty);
       * entity.setFellowship( Diceroller.getInstance().rollXdY(2, ten) +
       * thirty); entity.setMovement(4); break; case 3: // HUMAN
       * entity.setWeaponSkill( Diceroller.getInstance().rollXdY(2, ten) +
       * twenty); entity.setBallisticSkill( Diceroller.getInstance().rollXdY(2,
       * ten) + twenty); entity.setStrength( Diceroller.getInstance().rollXdY(2,
       * ten) + twenty); entity.setToughness(
       * Diceroller.getInstance().rollXdY(2, ten) + twenty); entity.setAgility(
       * Diceroller.getInstance().rollXdY(2, ten) + twenty);
       * entity.setIntelligence( Diceroller.getInstance().rollXdY(2, ten) +
       * twenty); entity.setWillPower( Diceroller.getInstance().rollXdY(2, ten)
       * + twenty); entity.setFellowship( Diceroller.getInstance().rollXdY(2,
       * ten) + twenty); entity.setMovement(4); break; } entity.setAttacks(1);
       * entity.setWounds(getWounds((int) entity.getRace().getId(),
       * Diceroller.getInstance().rolldX(ten)));
       * entity.setStrengthBonus(entity.getStrength() / ten);
       * entity.setToughnessBonus(entity.getToughness() / ten);
       * entity.setMagic(0); entity.setInsanityPoints(0);
       * entity.setFatePoints(getFatePoints((int) entity.getRace().getId(),
       * Diceroller.getInstance().rolldX(ten))); // assign racial skills if
       * (entity.getSkills() == null) { entity.setSkills(new
       * ArrayList<WFRPSkillEntity>()); } if (entity.getRace().getSkills() !=
       * null) { for (int i = 0, len = entity.getRace().getSkills().size(); i <
       * len; i++) { System.out.println("adding skill " +
       * entity.getRace().getSkills().get(i).getName());
       * entity.getSkills().add(entity.getRace().getSkills().get(i)); } } //
       * assign racial talents if (entity.getTalents() == null) {
       * entity.setTalents(new ArrayList<WFRPTalentEntity>()); } if
       * (entity.getRace().getTalents() != null) { for (int i = 0, len =
       * entity.getRace().getTalents().size(); i < len; i++) {
       * System.out.println("adding talent " +
       * entity.getRace().getTalents().get(i).getName());
       * entity.getTalents().add(entity.getRace().getTalents().get(i)); } }
       * switch ((int) entity.getRace().getId()) { case 3: // HUMAN /*
       * entity.getTalents().add((WFRPTalentEntity)
       * WFRPTalentController.getInstance().getByName( getRandomTalent((int)
       * entity.getRace().getId(), Diceroller.getInstance().rolldX(100))));
       */
    /*
     * case 2: // HALFLING /* entity.getTalents().add((WFRPTalentEntity)
     * WFRPTalentController.getInstance().getByName( getRandomTalent((int)
     * entity.getRace().getId(), Diceroller.getInstance().rolldX(100))));
     *//*
       * break; } } private String getRandomTalent(final int race, final int
       * roll) { System.out.println("getRandomTalent("+race+", "+roll); String s
       * = ""; switch (race) { case 2:// HALFLING switch (roll) { case 1: case
       * 2: case 3: case 4: case 5: s = "Acute Hearing"; break; case 6: case 7:
       * case 8: case 9: case 10: s = "Ambidextrous"; break; case 11: case 12:
       * case 13: case 14: case 15: s = "Coolheaded"; break; case 16: case 17:
       * case 18: case 19: case 20: s = "Excellent Vision"; break; case 21: case
       * 22: case 23: case 24: case 25: s = "Fleet Footed"; break; case 26: case
       * 27: case 28: case 29: s = "Hardy"; break; case 30: case 31: case 32:
       * case 33: s = "Lightning Reflexes"; break; case 34: case 35: case 36:
       * case 37: case 38: s = "Luck"; break; case 39: case 40: case 41: case
       * 42: s = "Marksman"; break; case 43: case 44: case 45: case 46: case 47:
       * s = "Mimic"; break; case 48: case 49: case 50: case 51: s =
       * "Resistance to Disease"; break; case 52: case 53: s =
       * "Resistance to Magic"; break; case 54: case 55: case 56: case 57: s =
       * "Resistance to Poison"; break; case 58: case 59: case 60: case 61: case
       * 62: s = "Savvy"; break; case 63: case 64: case 65: case 66: case 67: s
       * = "Sixth Sense"; break; case 68: case 69: case 70: case 71: case 72: s
       * = "Strong-minded"; break; case 73: case 74: case 75: case 76: case 77:
       * s = "Sturdy"; break; case 78: case 79: case 80: case 81: case 82: s =
       * "Suave"; break; case 83: case 84: case 85: case 86: case 87: s =
       * "Super Numerate"; break; case 88: case 89: case 90: case 91: s =
       * "Very Resilient"; break; case 92: case 93: case 94: case 95: s =
       * "Very Strong"; break; case 96: case 97: case 98: case 99: case 100: s =
       * "Warrior Born"; break; } break; case 3:// HUMAN switch (roll) { case 1:
       * case 2: case 3: case 4: s = "Acute Hearing"; break; case 5: case 6:
       * case 7: case 8: case 9: s = "Ambidextrous"; break; case 10: case 11:
       * case 12: case 13: s = "Coolheaded"; break; case 14: case 15: case 16:
       * case 17: case 18: s = "Excellent Vision"; break; case 19: case 20: case
       * 21: case 22: s = "Fleet Footed"; break; case 23: case 24: case 25: case
       * 26: case 27: s = "Hardy"; break; case 28: case 29: case 30: case 31: s
       * = "Lightning Reflexes"; break; case 32: case 33: case 34: case 35: s =
       * "Luck"; break; case 36: case 37: case 38: case 39: case 40: s =
       * "Marksman"; break; case 41: case 42: case 43: case 44: s = "Mimic";
       * break; case 45: case 46: case 47: case 48: case 49: s = "Night Vision";
       * break; case 50: case 51: case 52: case 53: s = "Resistance to Disease";
       * break; case 54: case 55: case 56: case 57: s = "Resistance to Magic";
       * break; case 58: case 59: case 60: case 61: s = "Resistance to Poison";
       * break; case 62: case 63: case 64: case 65: case 66: s = "Savvy"; break;
       * case 67: case 68: case 69: case 70: case 71: s = "Sixth Sense"; break;
       * case 72: case 73: case 74: case 75: s = "Strong-minded"; break; case
       * 76: case 77: case 78: case 79: s = "Sturdy"; break; case 80: case 81:
       * case 82: case 83: s = "Suave"; break; case 84: case 85: case 86: case
       * 87: s = "Super Numerate"; break; case 88: case 89: case 90: case 91: s
       * = "Very Resilient"; break; case 92: case 93: case 94: case 95: s =
       * "Very Strong"; break; case 96: case 97: case 98: case 99: case 100: s =
       * "Warrior Born"; break; } break; } return s; }
       */
}
