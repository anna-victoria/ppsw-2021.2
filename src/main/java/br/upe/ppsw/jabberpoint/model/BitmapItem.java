/**
 * <p>
 * UPE - Campus Garanhuns Curso de Engenharia de Software Disciplina de Padrões de Projeto de
 * Software Copyright 2021 the original author or authors.
 * </p>
 * 
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * </p>
 * 
 * @author Ian F. Darwin, hbarreiros
 */
package br.upe.ppsw.jabberpoint.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.util.ResourceUtils;

import br.upe.ppsw.jabberpoint.view.Style;

/**
 * Representa um item de {@link Slide} do tipo imagem.
 */
public class BitmapItem extends SlideItem {

  private BufferedImage bufferedImage;
  private String imageName;

  protected static final String FILE = "Arquivo ";
  protected static final String NOTFOUND = " não encontrado";

  /**
   * Cria uma nova instância de item de slide do tipo imagem, indicando sua posição em nível no
   * slide.
   * 
   * @param level o nível ocupado pelo item
   * @param string o nome do arquivo de imagem
   */
  public BitmapItem(int level, String name) {
    super(level);

    imageName = name;

    try {
      bufferedImage = ImageIO.read(ResourceUtils.getFile(imageName).getAbsoluteFile());
    } catch (IOException e) {
      System.err.println(FILE + imageName + NOTFOUND);
    }

  }

  /**
   * Inicializa um item do tipo imagem no nível mais externo e sem dado de imagem associado.
   */
  public BitmapItem() {
    this(0, null);
  }

  /**
   * Recupera o nome da imagem associada.
   * 
   * @return Uma instância de {@link String} contendo o nome do arquivo de imagem.
   */
  public String getName() {
    return imageName;
  }

  /**
   * @see SlideItem#getBoundingBox(Graphics, ImageObserver, float, Style)
   */
  public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
    return new Rectangle((int) (myStyle.indent * scale), 0,
        (int) (bufferedImage.getWidth(observer) * scale),
        ((int) (myStyle.leading * scale)) + (int) (bufferedImage.getHeight(observer) * scale));
  }

  /**
   * @see SlideItem#draw(int, int, float, Graphics, Style, ImageObserver)
   */
  public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
    int width = x + (int) (myStyle.indent * scale);
    int height = y + (int) (myStyle.leading * scale);

    g.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth(observer) * scale),
        (int) (bufferedImage.getHeight(observer) * scale), observer);
  }

  public String toString() {
    return "BitmapItem[" + getLevel() + "," + imageName + "]";
  }
  

 // @Override
  //public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
    // TODO Auto-generated method stub
//  return null;
  //}

 // @Override
 // public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
    // TODO Auto-generated method stub
    
  
}
