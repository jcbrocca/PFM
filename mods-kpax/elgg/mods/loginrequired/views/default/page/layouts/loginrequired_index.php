<?php
/**
 * Loginrequired Login page layout
 *
 */

$mod_params = array('class' => 'elgg-module-highlight');
?>

<!-- El header l'hem fet prèviament -->

<div class="loginrequired-index elgg-main elgg-grid clearfix">
<!-- Caixa principal -->

<div class="content">
<h2>¡Juguemos en serio!</h2>
<p>kPAX es una plataforma para aprender jugando. Aquí todo el mundo es bienvenido.</p>
<p>¿Te animas a entrar?</p>
</div>

<div class="coslogin">


		
<div class="elgg-inner pvm">
	<div class="elgg-module elgg-module-highlight " >
		<div class="elgg-body">
			<div class="elgg-module  elgg-module-aside" >
<!--				<div class="elgg-head">
					<h3>Iniciar sesión</h3>
				    </div>
-->
				<div class="elgg-body">
<!--					<form method="post" action="http://localhost/elgg-1.8.8/actions/login" class="elgg-form elgg-form-login">
					<fieldset>
						<input type="hidden" name="__elgg_token" value="08f57fe87f5a2af05e20f54948349230" />
						<input type="hidden" name="__elgg_ts" value="1336680455" />
						<div>
							<label>Nombre de usuario</label>
							<input type="text" value="" name="username" class="elgg-input-text elgg-autofocus" />
						</div>
						<div>
							<label>Contraseña</label>
							<input type="password" value="" name="password" class="elgg-input-password" />
						</div>
						<div class="elgg-foot">
							<input type="submit" value="Acceder" class="elgg-button elgg-button-submit" />
							<ul class="elgg-menu elgg-menu-general mtm">
								<li><a class="registration_link" href="http://localhost/elgg-1.8.8/actions/register">Darse de alta</a></li>
								<li><a class="forgot_link" href="http://localhost/elgg/forgotpassword">¿Contraseña perdida?</a></li>
							</ul>
						</div>
					</fieldset>
					</form>
-->
<?php
$top_box = $vars['login'];
echo elgg_view_module('featured',  '', $top_box, $mod_params);
//echo elgg_view("index/lefthandside");
?>
				</div>
			</div>
		</div>
	</div>
</div>
        
</div>
</div>