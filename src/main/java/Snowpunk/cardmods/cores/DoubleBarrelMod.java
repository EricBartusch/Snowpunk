package Snowpunk.cardmods.cores;

import Snowpunk.actions.ScatterDamageAction;
import Snowpunk.cardmods.cores.effects.AbstractCardEffectMod;
import Snowpunk.cards.cores.AbstractCoreCard;
import Snowpunk.cards.cores.AssembledCard;
import Snowpunk.cards.cores.util.OnUseCardInstance;
import Snowpunk.util.Wiz;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class DoubleBarrelMod extends AbstractCardEffectMod {
    public DoubleBarrelMod(String description, boolean secondVar) {
        super(description, secondVar);
        this.priority = -1;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (card instanceof AssembledCard) {
            ((AssembledCard) card).addUseEffects(new OnUseCardInstance(priority, (p, m) -> {
                int amount = useSecondVar ? ((AssembledCard) card).secondDamage : card.damage;
                Wiz.atb(new ScatterDamageAction(card, amount, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                Wiz.atb(new ScatterDamageAction(card, amount, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }));
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DoubleBarrelMod(description, useSecondVar);
    }
}
