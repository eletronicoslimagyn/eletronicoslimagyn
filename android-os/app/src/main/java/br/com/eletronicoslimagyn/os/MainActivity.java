package br.com.eletronicoslimagyn.os;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.net.Uri;
import android.content.Intent;
import android.widget.Toast;
import android.webkit.SslErrorHandler;
import android.net.http.SslError;
public class MainActivity extends Activity {
 private WebView web;
 private static final String HOME="https://eletronicoslimagyn.github.io/eletronicoslimagyn/";
 @Override public void onCreate(Bundle b){super.onCreate(b);
   web=new WebView(this);setContentView(web);
   WebSettings settings=web.getSettings(); settings.setJavaScriptEnabled(true);settings.setDomStorageEnabled(true);
   settings.setAllowFileAccess(false);settings.setAllowContentAccess(false);
   settings.setJavaScriptCanOpenWindowsAutomatically(false);
   settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
   settings.setSafeBrowsingEnabled(true);
   web.setWebChromeClient(new WebChromeClient());
   web.setWebViewClient(new WebViewClient(){
     @Override public boolean shouldOverrideUrlLoading(WebView view, android.webkit.WebResourceRequest request){
       Uri uri=request.getUrl(); String host=uri.getHost();
       if("https".equals(uri.getScheme()) && "eletronicoslimagyn.github.io".equalsIgnoreCase(host))return false;
       try{startActivity(new Intent(Intent.ACTION_VIEW,uri));}catch(Exception e){Toast.makeText(MainActivity.this,"Não foi possível abrir o link",Toast.LENGTH_SHORT).show();}
       return true;
     }
     @Override public void onReceivedSslError(WebView view,SslErrorHandler handler,SslError error){handler.cancel();Toast.makeText(MainActivity.this,"Conexão não segura",Toast.LENGTH_SHORT).show();}
   });
   web.loadUrl(HOME);
 }
 @Override public void onBackPressed(){ if(web!=null && web.canGoBack())web.goBack(); else super.onBackPressed(); }
 @Override protected void onDestroy(){if(web!=null){web.destroy();web=null;}super.onDestroy();}
}
