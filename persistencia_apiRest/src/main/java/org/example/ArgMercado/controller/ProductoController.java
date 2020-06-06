package org.example.ArgMercado.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ArgMercado.modelo.ImagenProducto;
import org.example.ArgMercado.modelo.ImagenWapper;
import org.example.ArgMercado.modelo.Producto;
import org.example.ArgMercado.servicio.IImagenProductoService;
import org.example.ArgMercado.servicio.ProductoService;
import org.hibernate.engine.jdbc.BlobImplementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

//Indiciamos que es un controlador rest
@CrossOrigin(origins = "*",allowedHeaders = "*",exposedHeaders = "content-type=multipart/*")
@RestController
@RequestMapping("/api") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/
public class ProductoController {

    @Autowired(required = true)
    private ProductoService productoService;

    @Autowired(required = true)
    private IImagenProductoService imagenProductoService;

    private List<ImagenProducto> imagenes = new ArrayList<>();

    private ImagenProducto imagen;

    private Blob sb;

    private Blob b;

    private Producto p;

    @PostMapping("/imagen")
    public void guardarImagenProducto(@Valid @RequestBody ImagenProducto imagenProducto) {
        imagenProductoService.guardar(imagenProducto);
    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
     http://localhost:8080/api/publicarProducto/8080/api/producto*/
    @GetMapping("/imagen/{idImagen}")
    public ImagenProducto recuperarImagenProducto(@PathVariable int idImagen) {
        return imagenProductoService.recuperar(idImagen);
    }

    @GetMapping("/imagenes")
    public List<ImagenProducto> recuperarTodasLasImagenProducto() throws JsonParseException, IOException {
        List<ImagenProducto> imagenProductos = getImagenProductos(this.imagenProductoService.recuperarTodo());

        return this.imagenProductoService.recuperarTodo();
       // return imagenProductos;
    }


    @DeleteMapping("/eliminarImagenes")
    public void borrarTodasLasImagenProducto() {
        this.imagenProductoService.borrarTodo();
    }

    @PostMapping(value = "/newFiles", consumes = {"multipart/form-data", MediaType.APPLICATION_JSON_VALUE})
    public void guardarFiles(@RequestParam("files") MultipartFile[] files ) {

        try {

            for (int i = 0; i < files.length; i++) {
                System.out.println("Original Image Byte Size - " + files[i].getBytes().length);

                this.imagen = new ImagenProducto();

                this.imagen.setFoto(this.compressBytes(files[i].getBytes()));

                this.imagen.setNombre(files[i].getOriginalFilename());

                this.imagen.setTipo(files[i].getContentType());

                this.imagenes.add(this.imagen);

                System.out.println("Comprimida Image Byte Size - " + imagen.getFoto().length);
            }

        } catch(IOException e) {

            e.printStackTrace();
        }

    }

    /*Este método se hará cuando por una petición Post (como indica la anotación) se llame a la url
             http://localhost:8080/api/publicarProducto*/
    //@PostMapping(value = "/publicarProducto",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.ALL_VALUE})
    @PostMapping("/publicarProducto")
    public void guardar(@Valid @RequestBody Producto producto) {

        for(ImagenProducto imagen : this.imagenes) {
            imagen.setOwner(producto);
        }

        producto.setImagenes(this.imagenes);

        this.productoService.guardar(producto);
    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url
     http://localhost:8080/api/publicarProducto/8080/api/producto*/
    @GetMapping("/producto/{idProducto}")
    public Producto recuperar(@PathVariable int idProducto) {
         Producto producto = productoService.recuperar(idProducto);
        List<ImagenProducto> imagenesProductos = getImagenProductos(producto.getImagenes());
        producto.setImagenes(imagenesProductos);
        return producto;
    }

    private List<ImagenProducto> getImagenProductos(List<ImagenProducto> imagenes) {
        List<ImagenProducto> imagenesProductos = new ArrayList<>();

        for (ImagenProducto imagen : imagenes) {
            System.out.println("Comprimida Image Byte Size - " + imagen.getFoto().length);
            imagen.setFoto(this.decompressBytes(imagen.getFoto()));
            imagenesProductos.add(imagen);
            System.out.println("Original Image Byte Size - " + imagen.getFoto().length);
        }
        return imagenesProductos;
    }

    @GetMapping("/productos")
    public List<Producto> recuperarTodo() {
        List<Producto> lsp = new ArrayList<>();
        List<ImagenProducto> imagenesProductos ;

        for(Producto producto : productoService.recuperarTodo()) {
            imagenesProductos = getImagenProductos(producto.getImagenes());
            producto.setImagenes(imagenesProductos);
            lsp.add(producto);
        }

        return lsp;
    }

    @GetMapping("/productosOrdenadosDeMenorAMayor")
    public List<Producto> recuperarProductosDeMenorAMayor() {
        return this.productoService.recuperarProductosDeMenorAMayor();
    }


    @DeleteMapping("/sacarPublicacionesDeProductos")
    public void borrarTodo() {
        this.productoService.borrarTodo();
    }


    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
            ioe.getStackTrace();
        } catch (DataFormatException e) {
            e.getStackTrace();
        }
        return outputStream.toByteArray();
    }

}
