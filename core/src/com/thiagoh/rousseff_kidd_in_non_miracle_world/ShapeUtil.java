package com.thiagoh.rousseff_kidd_in_non_miracle_world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by thiago on 09/03/16.
 */
public class ShapeUtil {

   public static final void scale(final MapLayer layer, float scale) {

      MapObjects objects = layer.getObjects();

      for (int i = 0; i < objects.getCount(); i++) {
         MapObject object = objects.get(i);

         scale(object, scale);
      }
   }

   public static final MapObject scale(final MapObject object, float scale) {

      if (object instanceof PolygonMapObject) {

         PolygonMapObject polygonMapObject = (PolygonMapObject) object;
         Polygon polygon = polygonMapObject.getPolygon();

         scale(polygon, scale);

         Gdx.app.debug("ShapeUtil", String.format("PolygonMapObject x,y (%.2f,%.2f) width,height (%.2f,%.2f)", polygon.getX(), polygon.getY(), polygon.getBoundingRectangle().width, polygon.getBoundingRectangle().height));
      } else if (object instanceof RectangleMapObject) {

         RectangleMapObject rectangleMapObject = (RectangleMapObject) object;
         Rectangle rectangle = rectangleMapObject.getRectangle();

         scale(rectangle, scale);

         Gdx.app.debug("ShapeUtil", String.format("RectangleMapObject x,y (%.2f,%.2f) width,height (%.2f,%.2f)", rectangle.getX(), rectangle.getY(), rectangle.width, rectangle.height));

      } else {
         throw new RuntimeException("Type not supported: " + object.getClass().getName());
      }

      return object;
   }

   public static final Polygon scale(final Polygon polygon, float scale) {

      polygon.setScale(scale, scale);
      polygon.setPosition(polygon.getX() * scale, polygon.getY() * scale);

      return polygon;
   }

   public static final void fillPolygon(final MapObject object, final Polygon polygon) {

      polygon.setPosition(0, 0);
      polygon.setScale(1f, 1f);
      polygon.setOrigin(0, 0);
      polygon.setRotation(0);

      if (object instanceof PolygonMapObject) {
         PolygonMapObject polygonMapObject = (PolygonMapObject) object;
         Polygon objectPolygon = polygonMapObject.getPolygon();

         polygon.setVertices(objectPolygon.getVertices());
         polygon.setPosition(objectPolygon.getX(), objectPolygon.getY());
         polygon.setScale(objectPolygon.getScaleX(), objectPolygon.getScaleY());
         polygon.setOrigin(objectPolygon.getOriginX(), objectPolygon.getOriginY());
         polygon.setRotation(objectPolygon.getRotation());
      } else if (object instanceof RectangleMapObject) {

         RectangleMapObject rectangleMapObject = (RectangleMapObject) object;
         Rectangle rectangle = rectangleMapObject.getRectangle();

         polygon.setVertices(new float[]{0, 0, rectangle.width, 0, rectangle.width, rectangle.height, 0, rectangle.height});
         polygon.setPosition(rectangle.x, rectangle.y);
      } else {
         throw new RuntimeException("No such type: " + object.getClass().getName());
      }
   }

   public static final Rectangle scale(final Rectangle rectangle, float scale) {

      rectangle.setWidth(rectangle.getWidth() * scale);
      rectangle.setHeight(rectangle.getHeight() * scale);
      rectangle.setPosition(rectangle.getX() * scale, rectangle.getY() * scale);

      return rectangle;
   }
}
