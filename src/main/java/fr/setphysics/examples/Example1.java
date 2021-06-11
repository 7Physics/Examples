package fr.setphysics.examples;

import fr.setphysics.common.geom.Position;
import fr.setphysics.common.geom.Vec3;
import fr.setphysics.common.geom.shape.Cuboid;
import fr.setphysics.common.geom.shape.Sphere;
import fr.setphysics.engine.World;
import fr.setphysics.renderer.Camera;
import fr.setphysics.renderer.Scene3D;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Example1 {
    public static void main(String... args) {
        JFrame frame = new JFrame("Exemple 7Physics");

        Camera camera = new Camera(new Position(-3, 1, 0));
        camera.rotateVertical(-Math.PI/8);

        // On crée la scène 3D
        Scene3D scene = new Scene3D(camera);
        // C'est un panel comme les autres, on peut lui modifier ses dimensions et l'ajouter à un panel
        scene.setPreferredSize(new Dimension(1000, 600));
        frame.add(scene);

        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // On crée le world qui contiendra les objets physiques
        World world = new World();
        world.setGravity(new Vec3(0, -1, 0));

        Position wallPosition = new Position(0, .15, -1);

        scene.addObject(new Cuboid(3, .1, 2))
                .setPosition(wallPosition);
        world.addPhysicObject(new Cuboid(3, .1, 2))
                .setPosition(wallPosition)
                .setDynamic(false);

        Random r = new Random();
        r.nextFloat();

        for(int i = 0; i < 100; i++) {
            // Position partagée entre l'objet graphique et l'objet physique : fait le lien entre eux.
            Position spherePosition = new Position(r.nextFloat()*6-3, (i+2)/3f, r.nextFloat()*6-3);

            // On ajoute la sphère graphique
            scene.addObject(new Sphere(.1, 3))
                    .setPosition(spherePosition)
                    .setColor(Color.ORANGE, Color.RED);
            // On ajoute la sphère physique
            world.addPhysicObject(new Sphere(.1, 3))
                    .setPosition(spherePosition)
                    .addForce(new Vec3(r.nextFloat()*4-2, 0, r.nextFloat()*4-2));
        }

        // On lance la simulation à 60 mise à jour par seconde
        world.startStepLoop(60);
    }

}
