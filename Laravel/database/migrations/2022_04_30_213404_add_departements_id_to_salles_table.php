<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddDepartementsIdToSallesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('salles', function (Blueprint $table) {
            $table->foreign('departement_id')->references('id')->on('departements')
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
        Schema::table('salles', function (Blueprint $table) {
            $table->dropForeign(['departement_id']);
            $table->dropColumn('departement_id');
        });
    }
}
