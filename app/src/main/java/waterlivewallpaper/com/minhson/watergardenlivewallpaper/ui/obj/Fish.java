package waterlivewallpaper.com.minhson.watergardenlivewallpaper.ui.obj;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import waterlivewallpaper.com.minhson.watergardenlivewallpaper.common.Assets;
import waterlivewallpaper.com.minhson.watergardenlivewallpaper.common.Constant;

/**
 * Created by Administrator on 24/10/2017.
 */

public class Fish {
    private Array<TextureAtlas.AtlasRegion> list;
    private Array<TextureAtlas.AtlasRegion> fish;
    private Animation animation;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private int width;
    private int height;
    private int a, b, typeX, typeY, state, speedX, speedY;
    private int lastPost, rPos;
    private float runTime;

    public Fish(int type) {

        switch (type) {
            case 1:
                list = Assets.instance.assetFish1.list;
                break;
            case 2:
                list = Assets.instance.assetFish2.list;
                break;
            case 3:
                list = Assets.instance.assetFish3.list;
                break;
            case 4:
                list = Assets.instance.assetFish4.list;
                break;
            case 5:
                list = Assets.instance.assetFish5.list;
                break;
            default:
                break;
        }

        typeX = new Random().nextInt(2);
        typeY = new Random().nextInt(2);
        speedX = new Random().nextInt(80) + 80;
        speedY = new Random().nextInt(80) + 80;
        float ratio = list.get(0).getRegionWidth() / (float) list.get(0).getRegionHeight();
        this.width = Constant.WIDTH / 4;
        this.height = (int) (width / ratio);
        int x, y;
        if (typeX == Constant.LEFT) {
            x = 0;
        } else {
            x = Constant.WIDTH * 2 - width;
        }
        y = new Random().nextInt(Constant.HEIGHT - height);
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(400, 400);
        setAnimation();
        state = Constant.RUN;
    }

    public void update() {
        this.runTime += Gdx.graphics.getDeltaTime();
        wallY();
        wallX();
        rTurn();
        if (state == Constant.RUN) {
            if (wallX() || rTurn()) {
                state = Constant.PRETURN;
            }
        }
        switch (state) {
            case Constant.RUN:
                position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime()));
                break;
            case Constant.PRETURN:
                preTurn();
                break;
            case Constant.TURN:
                turn();
                break;
            case Constant.PREFAST:
                prefast();
                break;
            case Constant.FAST:
                fast();
                break;
            case Constant.SLOW:
                slow();
                break;
            default:
                break;
        }
    }

    public void draw(SpriteBatch batch, int mPos) {
        update();
        batch.draw(animation.getKeyFrame(runTime), position.x + mPos, position.y, width, height);
    }

    public void onTouch(int screenX, int screenY) {
        if (state == Constant.RUN) {
            if (touch1(screenX, screenY, 50)) {
                if (touch2(screenX)) {
                    state = Constant.PRETURN;
                } else {
                    state = Constant.PREFAST;
                }
            }
        }
        Log.e("OnTouch", "Oke");
    }

    public void preTurn() {
        runTime = 0f;
        if (typeX == Constant.LEFT) {
            a = 60;
            b = 80;
        } else {
            a = 20;
            b = 40;
        }
        typeY = new Random().nextInt(2);
        updateAnimation();
        state = Constant.TURN;
    }

    public void prefast() {
        setPos();
        if (typeX == Constant.LEFT) {
            acceleration.x = Constant.ACCELERATION;
        } else {
            acceleration.x = -Constant.ACCELERATION;
        }
        if (typeY == Constant.DOWN) {
            acceleration.y = Constant.ACCELERATION;
        } else {
            acceleration.y = -Constant.ACCELERATION;
        }
        state = Constant.FAST;
    }

    public void fast() {
        velocity.add(acceleration.cpy().scl(Gdx.graphics.getDeltaTime()));
        if (Math.abs(velocity.x) > 400) {
            state = Constant.SLOW;
        }
        position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime()));
    }

    public void slow() {
        velocity.sub(acceleration.cpy().scl(Gdx.graphics.getDeltaTime()));
        if (Math.abs(velocity.x) <= speedX) {
            state = Constant.RUN;
        }
        position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime()));
    }

    public void turn() {
        int frameNumber = ((int) (runTime / animation.frameDuration)) % 20;
        if (frameNumber > 5) {
            setTypeX2();
            setTypeY();
            position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime()));
        }
        if (frameNumber == 19) {
            typeX = 1 - typeX;
            setAnimation();
            state = Constant.RUN;
        }
    }

    public boolean rTurn() {
        if (typeX == Constant.LEFT && position.x < Constant.WIDTH * 2 - width || typeX == Constant.RIGHT && position.x > 0) {
            if (Math.abs(position.x - lastPost) > rPos) {
                return true;
            }
        }
        return false;
    }

    public boolean touch1(int screenX, int screenY, int size) {
        if (screenX >= position.x - size && screenX <= position.x + width + size &&
                screenY >= position.y - size && screenY <= position.y + height + size) {
            return true;
        }
        return false;
    }

    public boolean touch2(int screenX) {
        int x = (int) ((2 * position.x + width) / 2);
        if (typeX == Constant.LEFT && screenX >= x) {
            return true;
        }
        if (typeX == Constant.RIGHT && screenX <= x) {
            return true;
        }
        return false;
    }


    public boolean wallX() {
        if (typeX == Constant.RIGHT && position.x <= 0 || typeX == Constant.LEFT && position.x >= Constant.WIDTH * 2 - width) {
            return true;
        }
        return false;
    }

    public void wallY() {
        if (typeY == Constant.UP && position.y <= 0 || typeY == Constant.DOWN && position.y >= Constant.HEIGHT - height) {
            typeY = 1 - typeY;
            setTypeY();
        }
    }

    public Array<TextureAtlas.AtlasRegion> subList(int a, int b) {
        Array<TextureAtlas.AtlasRegion> tmp = new Array<>();
        for (int i = a; i < b; i++) {
            tmp.add(list.get(i));
        }
        return tmp;
    }

    public void setTypeX() {
        if (typeX == Constant.LEFT) {
            a = 40;
            b = 60;
            velocity.x = speedX;
        } else {
            a = 0;
            b = 20;
            velocity.x = -speedX;
        }
    }

    public void setTypeX2() {
        if (typeX == Constant.LEFT) {
            velocity.x = -speedX;
        } else {
            velocity.x = +speedX;
        }
    }

    public void setTypeY() {
        if (typeY == Constant.UP) {
            velocity.y = -speedY;
        } else {
            velocity.y = speedY;
        }
    }

    public void setAnimation() {
        setTypeX();
        setTypeY();
        updateAnimation();
        setPos();
    }

    public void setPos() {
        lastPost = (int) position.x;
        rPos = new Random().nextInt(Constant.WIDTH) + Constant.WIDTH / 2;
    }

    public void updateAnimation() {
        fish = subList(a, b);
        animation = new Animation(0.05f, fish);
        animation.setPlayMode(Animation.LOOP);

    }
}
