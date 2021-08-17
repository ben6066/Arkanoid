import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * @author Ben Azran <ben6066@gmail.com> 208276162
 */
public class GameLevel implements HitListener, Animation {
    //Game's fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksRemained;
    private Counter ballsRemained;
    private Counter currentScore;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation info;

    /**
     * Instantiates a new Game.
     * The constructor initializes the sprites collection, the game environment and the gui
     * @param info - level's information
     * @param currentScore - the current score
     * @param gui - the GUI
     */
    public GameLevel(LevelInformation info, Counter currentScore, GUI gui) {
        this.info = info;
        this.runner = new AnimationRunner(gui);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksRemained = new Counter(this.info.numberOfBlocksToRemove());
        this.ballsRemained = new Counter(this.info.numberOfBalls());
        this.keyboard = null;
        this.currentScore = currentScore;
    }

    /**.
     * The method adds a new collidable object to game environment
     * @param c - the collidable object to add the the game environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**.
     * The method adds a new sprite object to sprite collection
     * @param s - the sprite object to add the the sprite collection
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**.
     * The method removes a block from the collidables collection
     * @param c - the block to remove from the collidables collection
     */
    public void removeCollidable(Collidable c) {
        // Make a copy of the collidables list before iterating over it.
        List<Collidable> collidablesCopy = new ArrayList<Collidable>(this.environment.getCollidables());
        if (!collidablesCopy.isEmpty()) {
            for (int i = 0; i < collidablesCopy.size(); i++) {
                if (collidablesCopy.get(i).equals(c)) {
                    collidablesCopy.remove(i);
                    // Case removed - update the list
                    this.environment.updateList(collidablesCopy);
                    return;
                }
            }
        }
    }

    /**.
     * The method removes a sprite from the sprite collection
     * @param s - the sprite to remove from the sprite collection
     */
    public void removeSprite(Sprite s) {
        // Make a copy of the sprites list before iterating over it.
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.sprites.getSprites());
        if (!spritesCopy.isEmpty()) {
            for (int i = 0; i < spritesCopy.size(); i++) {
                if (spritesCopy.get(i).equals(s)) {
                    spritesCopy.remove(i);
                    // Case removed - update the list
                    this.sprites.updateList(spritesCopy);
                    return;
                }
            }
        }
    }

    /**.
     * The method add the blocks to the game
     * @param br - a block remover
     * @param score - a score tracker
     */
    private void addBlocks(BlockRemover br, ScoreTrackingListener score) {
        for (int i = 0; i < this.info.blocks().size(); i++) {
            Block newBlock = this.info.blocks().get(i);
            newBlock.addHitListener(br);
            newBlock.addHitListener(score);
            newBlock.addToGame(this);
        }
    }

    /**.
     * @param level - a level
     * @return - the method returns the upper left x value of the vertex of the paddle
     */
    private double paddleXValAccordingToLevel(int level) {
        double screenMiddleXVal;
        double halfOfPaddleWidth;

        screenMiddleXVal = 400;
        halfOfPaddleWidth = this.info.paddleWidth() / 2;

        //Case 'Direct Hit', 'Green 3', 'Final Four' levels
        if (level == 1 || level == 3 || level == 4) {
            return screenMiddleXVal - halfOfPaddleWidth;
        }

        return 100;
    }

    /**.
     * The method creates balls on top of the paddle
     */
    public void createBallsOnTopOfPaddle() {
        this.keyboard = this.runner.getGUI().getKeyboardSensor();

        int levelNumber = 0;

        if (this.info.levelName().contentEquals("Direct Hit")) {
            levelNumber = 1;
        } else if (this.info.levelName().contentEquals("Wide Easy")) {
            levelNumber = 2;
        } else if (this.info.levelName().contentEquals("Green 3")) {
            levelNumber = 3;
        } else if (this.info.levelName().contentEquals("Final Four")) {
            levelNumber = 4;
        }

        double paddleXVal = paddleXValAccordingToLevel(levelNumber);

        //Paddle's upper left point
        Point paddlePoint = new Point(paddleXVal, 560);

        //Setting the paddle
        Paddle player = new Paddle(this.keyboard, paddlePoint, this.info.paddleWidth(), this.info.paddleSpeed());
        player.addToGame(this);

        //'Direct Hit' level
        if (this.info.numberOfBalls() == 1) {
            //Generating the ball's center
            Point center = new Point(paddleXVal + this.info.paddleWidth() / 2, paddlePoint.getY() - 4);
            //Creating the ball
            Ball ball = new Ball(center, 3, Color.WHITE);

            //Adding the ball to the game
            ball.addToGame(this);
            ball.setVelocity(this.info.initialBallVelocities().get(0));
            ball.setGameEnvironment(environment);

            //'Wide Easy' level
        } else if (this.info.numberOfBalls() == 10) {
            for (int i = 0; i < 10; i++) {
                //Generating the balls centers
                Point center = new Point(paddleXVal + this.info.paddleWidth() / 2, paddlePoint.getY() - 4);
                Ball ball = new Ball(center, 3, Color.WHITE);

                //Adding the ball to the game
                ball.addToGame(this);
                ball.setVelocity(this.info.initialBallVelocities().get(i));
                ball.setGameEnvironment(environment);
            }
            //'Green 3' level
        } else if (this.info.numberOfBalls() == 2) {
            //Generating first ball center
            Point center = new Point(paddlePoint.getX() + this.info.paddleWidth() / 2, paddlePoint.getY() - 4);
            Ball ball = new Ball(center, 3, Color.WHITE);

            //Adding the ball to the game
            ball.addToGame(this);
            ball.setVelocity(this.info.initialBallVelocities().get(0));
            ball.setGameEnvironment(environment);

            //Generating second ball center
            center = new Point(paddlePoint.getX() + this.info.paddleWidth() / 2, paddlePoint.getY() - 4);
            ball = new Ball(center, 3, Color.WHITE);

            //Adding the ball to the game
            ball.addToGame(this);
            ball.setVelocity(this.info.initialBallVelocities().get(1));
            ball.setGameEnvironment(environment);
            //'Final Four' level
        } else if (this.info.numberOfBalls() == 3) {
            //Generating first ball center
            Point center = new Point(paddlePoint.getX() + 20, paddlePoint.getY() - 4);
            Ball ball = new Ball(center, 3, Color.WHITE);

            //Adding the ball to the game
            ball.addToGame(this);
            ball.setVelocity(this.info.initialBallVelocities().get(0));
            ball.setGameEnvironment(environment);

            //Generating second ball center
            center = new Point(paddlePoint.getX() + this.info.paddleWidth() - 20, paddlePoint.getY() - 4);
            ball = new Ball(center, 3, Color.WHITE);

            //Adding the ball to the game
            ball.addToGame(this);
            ball.setVelocity(this.info.initialBallVelocities().get(1));
            ball.setGameEnvironment(environment);

            //Generating third ball center
            center = new Point(paddlePoint.getX() + (this.info.paddleWidth() / 2), paddlePoint.getY() - 4);
            ball = new Ball(center, 3, Color.WHITE);

            //Adding the ball to the game
            ball.addToGame(this);
            ball.setVelocity(this.info.initialBallVelocities().get(2));
            ball.setGameEnvironment(environment);
        }
    }

    /**
     * The method initializes a new game: create the Blocks and add them to the game.
     */
    public void initialize() {
        //Adding the background as a sprite to the sprites list
        addSprite(this.info.getBackground());

        //Declaration of a new Block Remover
        BlockRemover blockRemover = new BlockRemover(this, this.blocksRemained);

        //Declaration of a new Ball Remover
        BallRemover ballRemover = new BallRemover(this, this.ballsRemained);

        //Declaration of a new Score Tracker
        ScoreTrackingListener score = new ScoreTrackingListener(this.currentScore);

        //Declaring the Death Region and add the ball remover as a listener to it
        Block deathEdge = new Block(new Rectangle(new Point(0, 600), 800, 20), java.awt.Color.GRAY);
        deathEdge.addHitListener(ballRemover);

        //Setting the borders of the board
        Block upEdge = new Block(new Rectangle(new Point(0, 0), 800, 20), java.awt.Color.GRAY);
        Block leftEdge = new Block(new Rectangle(new Point(0, 0), 20, 600), java.awt.Color.GRAY);
        Block rightEdge = new Block(new Rectangle(new Point(780, 0), 20, 600), java.awt.Color.GRAY);
        deathEdge.addToGame(this);
        leftEdge.addToGame(this);
        rightEdge.addToGame(this);
        upEdge.addToGame(this);

        //Setting the score indicator
        ScoreIndicator si = new ScoreIndicator(this.currentScore);
        addSprite(si);

        //Setting the blocks
        addBlocks(blockRemover, score);
    }

    /**
     * The method runs the game -- start the animation loop.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the hitting Ball.
     * @param beingHit - the block being hit
     * @param hitter - the hitting ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.removeCollidable(beingHit);
        this.removeSprite(beingHit);
        beingHit.removeHitListener(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.setColor(java.awt.Color.BLACK);
        d.drawText(500, 15, "Level Name: " + this.info.levelName(), 15);

        //Pause screen display case 'p' key is pressed
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        // Case no blocks remained - give a bonus of 100 points
        if (this.blocksRemained.getValue() == 0) {
            this.currentScore.increase(100);
            this.running = false;
        }

        /* Case no balls remained - the player lost
         * Display a Game Over screen with the result
         */
        if (this.ballsRemained.getValue() == 0) {
            this.running = false;
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new GameOver(this.currentScore)));
            this.runner.getGUI().close();
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return The method returns the number of balls remained
     */
    public int numOfBallsRemained() {
        System.out.println(this.ballsRemained.getValue());
        return this.ballsRemained.getValue();
    }
    /**
     * @return The method returns the number of blocks remained
     */
    public int numOfBlocksRemained() {
        return this.blocksRemained.getValue();
    }
    /**
     * @return The method returns the animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return this.runner;
    }
}
