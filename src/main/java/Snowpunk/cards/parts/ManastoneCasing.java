package Snowpunk.cards.parts;

import Snowpunk.cardmods.BlockBuffMod;
import Snowpunk.cardmods.MagicBuffMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.function.Predicate;

import static Snowpunk.SnowpunkMod.makeID;

public class ManastoneCasing extends AbstractPartCard {
    public static final String ID = makeID(ManastoneCasing.class.getSimpleName());

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;

    public ManastoneCasing() {
        super(ID, TYPE, RARITY);
    }

    @Override
    public Predicate<AbstractCard> getFilter() {
        return c -> c.baseMagicNumber >= 2;
    }

    @Override
    public void apply(AbstractCard card) {
        CardModifierManager.addModifier(card, new MagicBuffMod(2));
    }
}