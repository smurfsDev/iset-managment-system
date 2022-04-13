<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDemandeCreationClub extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('demandeCreationClub', function (Blueprint $table) {
            $table->id();
            $table->string('sujet')->default('creation club');
            $table->string('nomClub');
            $table->text('logo');
            $table->date('dateCreation');
            $table->string('activite');
            $table->string('president');
            $table->string('vicePresident');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('demande_creation_club');
    }
}
