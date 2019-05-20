package com.lopatineugene.planets.util;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ClickListenerFactory {

    public static ClickListener clicked(final ClickedListener onClicked) {
        return new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClicked.clicked(event,x,y);
            }
        };
    }
}
