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
        Schema::create('demande_creation_clubs', function (Blueprint $table) {
            $table->id();
            $table->string('sujet')->default('creation club');
            $table->string('nomClub');
            $table->longText('logo');
            $table->date('dateCreation');
            $table->string('activite');
            $table->string('president');
            $table->string('vicePresident');
            $table->timestamps();
            $table->foreignId('responsableClubId');
            $table->foreign('responsableClubId')->references('id')->on('users')
                ->onDelete('cascade');
            $table->tinyInteger('status')->default(0);
            $table->foreignId('adminId')->nullable();
            $table->foreign('adminId')->references('id')->on('users')
                ->onDelete('cascade');
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
