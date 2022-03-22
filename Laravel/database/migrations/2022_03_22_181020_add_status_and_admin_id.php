<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddStatusAndAdminId extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('demandeCreationClub', function (Blueprint $table) {
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
        Schema::table('demandeCreationClub', function (Blueprint $table) {
            $table->dropForeign(['adminId']);
            $table->dropColumn('adminId');
            $table->dropColumn('status');
        });
    }
}
